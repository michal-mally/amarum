package pl.helenium.amarum.core.factory;

import org.apache.commons.io.IOUtils;
import pl.helenium.amarum.api.FactoryException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static org.apache.commons.lang3.Validate.notNull;

public class ClasspathPropertiesFactory extends AbstractFactory<Properties> {

    private final String location;

    public ClasspathPropertiesFactory(String location) {
        this.location = location;
    }

    @Override
    protected Properties doProduce() throws FactoryException {
        InputStream stream = null;
        try {
            stream = getInputStream();
            notNull(stream, "no resource available at classpath:%s", this.location);

            final Properties properties = new Properties();
            properties.load(stream);
            return properties;
        } catch (IOException e) {
            throw new FactoryException("Unable to load properties!", e);
        } finally {
            IOUtils.closeQuietly(stream);
        }
    }

    private InputStream getInputStream() {
        return this.getClass().getResourceAsStream(this.location);
    }

}
