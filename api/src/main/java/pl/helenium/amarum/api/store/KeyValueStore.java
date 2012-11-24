package pl.helenium.amarum.api.store;

import java.util.NavigableSet;

public interface KeyValueStore {

    NavigableSet<String> getAllKeys();

    String getValue(String key);

}
