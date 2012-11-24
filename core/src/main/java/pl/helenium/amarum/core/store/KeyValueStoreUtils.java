package pl.helenium.amarum.core.store;

import pl.helenium.amarum.api.store.KeyValueStore;

import java.util.NavigableMap;
import java.util.TreeMap;

public final class KeyValueStoreUtils {

    public static NavigableMap<String, String> asMap(KeyValueStore store) {
        final NavigableMap<String, String> entries = new TreeMap<String, String>();
        for (final String key : store.getAllKeys()) {
            entries.put(key, store.getValue(key));
        }

        return entries;
    }

    public KeyValueStoreUtils() {
        // intentionally left blank
    }
}
