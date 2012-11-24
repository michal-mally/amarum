package pl.helenium.amarum.core.factory.store;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.helenium.amarum.api.factory.Factory;
import pl.helenium.amarum.api.store.KeyValueStore;

import java.util.Map;
import java.util.regex.Pattern;

import static org.apache.commons.lang3.Validate.*;

public class FilterKeyValueStoreFactory extends AbstractInMemoryKeyValueStoreFactory {

    private static final Logger log = LoggerFactory.getLogger(FilterKeyValueStoreFactory.class);

    private final Factory<KeyValueStore> factory;

    private final Pattern[] patterns;

    public FilterKeyValueStoreFactory(Factory<KeyValueStore> factory, Pattern... patterns) {
        this.factory = notNull(factory);
        this.patterns = noNullElements(notEmpty(patterns));
    }

    @Override
    protected void fillEntries(Map<String, String> entries) throws Exception {
        final KeyValueStore keyValueStore = this.factory.produce();
        for (final String key : keyValueStore.getAllKeys()) {
            for (final Pattern pattern : this.patterns) {
                if (pattern.matcher(key).matches()) {
                    log.debug("Key {} matches pattern {} - adding to the map.", key, pattern);
                    entries.put(key, keyValueStore.getValue(key));
                    break;
                }
            }
        }
    }

}
