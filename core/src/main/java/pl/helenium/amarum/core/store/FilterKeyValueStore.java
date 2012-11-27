package pl.helenium.amarum.core.store;

import pl.helenium.amarum.api.store.KeyValueStore;
import pl.helenium.amarum.core.annotation.FactoryConstructor;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Pattern;

import static java.util.Arrays.asList;
import static org.apache.commons.lang3.Validate.*;

public class FilterKeyValueStore extends AbstractKeyValueStore {

    private final KeyValueStore store;

    private final List<Pattern> patterns;

    @FactoryConstructor
    public FilterKeyValueStore(KeyValueStore store, Pattern... patterns) {
        this.store = notNull(store);
        this.patterns = asList(noNullElements(notEmpty(notNull(patterns))));
    }

    @Override
    public Set<String> getAllKeys() {
        final Set<String> keys = new TreeSet<String>();
        for (final String key : store.getAllKeys()) {
            if (matchesAnyPattern(key)) {
                keys.add(key);
            }
        }

        return keys;
    }

    @Override
    public String getValue(String key) {
        return matchesAnyPattern(key) ? store.getValue(key) : null;
    }

    private boolean matchesAnyPattern(String key) {
        for (final Pattern pattern : patterns) {
            if (pattern.matcher(key).matches()) {
                return true;
            }
        }

        return false;
    }

}
