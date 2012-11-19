package pl.helenium.amarum.core.factory;

import pl.helenium.amarum.api.FactoryException;

import java.io.InputStream;

import static org.apache.commons.lang3.Validate.notNull;

public class ClasspathInputStreamFactory extends AbstractFactory<InputStream> {

    private final String location;

    public ClasspathInputStreamFactory(String location) {
        notNull(location, "Location mustn't be null!");
        this.location = location;
    }

    @Override
    protected InputStream doProduce() throws FactoryException {
        final InputStream stream = this.getClass().getResourceAsStream(this.location);
        notNull(stream, "No resource at classpath:%s", this.location);
        return stream;
    }

}
