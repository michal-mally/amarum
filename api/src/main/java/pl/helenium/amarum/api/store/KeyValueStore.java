package pl.helenium.amarum.api.store;

import java.util.Set;

public interface KeyValueStore {

    Set<String> getAllKeys();

    String getValue(String key);

}
