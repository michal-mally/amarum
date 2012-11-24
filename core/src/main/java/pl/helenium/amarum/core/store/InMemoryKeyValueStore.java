package pl.helenium.amarum.core.store;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import static org.apache.commons.lang3.Validate.notNull;

public class InMemoryKeyValueStore extends AbstractKeyValueStore {

    private static final Logger log = LoggerFactory.getLogger(InMemoryKeyValueStore.class);

    protected final Map<String, String> map;

    public InMemoryKeyValueStore(Map<String, String> map) {
        this.map = notNull(map, "map parameter mustn't be null");
    }

    public InMemoryKeyValueStore() {
        this(new TreeMap<String, String>());
    }

    @Override
    public Set<String> getAllKeys() {
        return map.keySet();
    }

    @Override
    public String getValue(String key) {
        return this.map.get(key);
    }

}
