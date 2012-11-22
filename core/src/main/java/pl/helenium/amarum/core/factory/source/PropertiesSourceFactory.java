package pl.helenium.amarum.core.factory.source;

import pl.helenium.amarum.api.Factory;
import pl.helenium.amarum.api.exception.FactoryException;

import java.util.Map;
import java.util.Properties;

import static org.apache.commons.lang3.Validate.notNull;

public class PropertiesSourceFactory extends AbstractInMemorySourceFactory {

    private final Factory<Properties> propertiesFactory;

    public PropertiesSourceFactory(Factory<Properties> propertiesFactory) {
        this.propertiesFactory = notNull(propertiesFactory);
    }

    @Override
    protected void fillEntries(Map<String, String> entries) throws FactoryException {
        final Properties properties = this.propertiesFactory.produce();
        for (final String propName : properties.stringPropertyNames()) {
            entries.put(propName, properties.getProperty(propName));
        }
    }

}