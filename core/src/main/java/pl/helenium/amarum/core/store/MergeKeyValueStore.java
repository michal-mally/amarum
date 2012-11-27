package pl.helenium.amarum.core.store;

import pl.helenium.amarum.api.store.KeyValueStore;
import pl.helenium.amarum.core.annotation.FactoryConstructor;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import static java.util.Arrays.asList;
import static java.util.Collections.reverse;
import static java.util.Collections.unmodifiableSet;
import static org.apache.commons.lang3.Validate.noNullElements;
import static org.apache.commons.lang3.Validate.notNull;

public class MergeKeyValueStore extends AbstractKeyValueStore {

    private final List<KeyValueStore> keyValueStores;

    @FactoryConstructor
    public MergeKeyValueStore(KeyValueStore... keyValueStores) {
        this.keyValueStores = asList(noNullElements(notNull(keyValueStores)));
        reverse(this.keyValueStores);
    }

    @Override
    public Set<String> getAllKeys() {
        final Set<String> keys = new TreeSet<String>();
        for (final KeyValueStore store : keyValueStores) {
            keys.addAll(store.getAllKeys());
        }

        return unmodifiableSet(keys);
    }

    @Override
    public String getValue(String key) {
        for (final KeyValueStore store : keyValueStores) {
            final String value = store.getValue(key);
            if (value != null) {
                return value;
            }
        }

        return null;
    }

}
