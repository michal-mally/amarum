package pl.helenium.amarum.core.store;

import pl.helenium.amarum.core.annotation.FactoryConstructor;

import java.util.Properties;
import java.util.Set;

import static org.apache.commons.lang3.Validate.notNull;

public class PropertiesKeyValueStore extends AbstractKeyValueStore {

    private final Properties properties;

    @FactoryConstructor
    public PropertiesKeyValueStore(Properties properties) {
        this.properties = notNull(properties);
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
