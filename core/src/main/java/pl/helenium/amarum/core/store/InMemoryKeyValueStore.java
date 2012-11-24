package pl.helenium.amarum.core.store;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.TreeMap;

import static org.apache.commons.lang3.Validate.notNull;

public class InMemoryKeyValueStore extends AbstractKeyValueStore {

    private static final Logger log = LoggerFactory.getLogger(InMemoryKeyValueStore.class);

    protected final NavigableMap<String, String> map;

    public InMemoryKeyValueStore(NavigableMap<String, String> map) {
        this.map = notNull(map, "map parameter mustn't be null");
    }

    public InMemoryKeyValueStore() {
        this(new TreeMap<String, String>());
    }

    @Override
    public NavigableSet<String> getAllKeys() {
        return map.navigableKeySet();
    }

    @Override
    public String getValue(String key) {
        return this.map.get(key);
    }

}
