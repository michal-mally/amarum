package pl.helenium.amarum.core.store;

import java.util.Properties;
import java.util.Set;

public class PropertiesKeyValueStore extends AbstractKeyValueStore {

    private final Properties properties;

    public PropertiesKeyValueStore(Properties properties) {
        this.properties = properties;
    }

    @Override
    public Set<String> getAllKeys() {
        return this.properties.stringPropertyNames();
    }

    @Override
    public String getValue(String key) {
        return this.properties.getProperty(key);
    }

}
