package pl.helenium.amarum.core.factory.properties;

import org.apache.commons.io.IOUtils;
import pl.helenium.amarum.api.exception.FactoryException;
import pl.helenium.amarum.api.factory.Factory;
import pl.helenium.amarum.core.factory.AbstractFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static org.apache.commons.lang3.Validate.notNull;

public class InputStreamPropertiesFactory extends AbstractFactory<Properties> {

    private final Factory<InputStream> factory;

    public InputStreamPropertiesFactory(Factory<InputStream> factory) {
        this.factory = notNull(factory, "Factory mustn't be null!");
    }

    @Override
    protected Properties doProduce() throws FactoryException {
        InputStream stream = null;
        try {
            stream = notNull(factory.produce(), "Null InputStream produced by factory: %s", this.factory);

            final Properties properties = new Properties();
            properties.load(stream);
            return properties;
        } catch (IOException e) {
            throw new FactoryException("Unable to load properties!", e);
        } finally {
            IOUtils.closeQuietly(stream);
        }
    }

}
