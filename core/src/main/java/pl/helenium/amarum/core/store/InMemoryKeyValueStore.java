package pl.helenium.amarum.core.store;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Set;

import static org.apache.commons.lang3.Validate.notNull;

public class InMemoryKeyValueStore extends AbstractKeyValueStore {

    @SuppressWarnings("unused")
    private static final Logger log = LoggerFactory.getLogger(InMemoryKeyValueStore.class);

    private final Map<String, String> map;

    public InMemoryKeyValueStore(Map<String, String> map) {
        this.map = notNull(map, "map parameter mustn't be null");
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
