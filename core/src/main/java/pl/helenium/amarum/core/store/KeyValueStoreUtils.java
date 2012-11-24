package pl.helenium.amarum.core.store;

import pl.helenium.amarum.api.store.KeyValueStore;

import java.util.Map;
import java.util.TreeMap;

public final class KeyValueStoreUtils {

    public static Map<String, String> asMap(KeyValueStore store) {
        final Map<String, String> entries = new TreeMap<String, String>();
        for (final String key : store.getAllKeys()) {
            entries.put(key, store.getValue(key));
        }

        return entries;
    }

    private KeyValueStoreUtils() {
        // intentionally left blank
    }

}
