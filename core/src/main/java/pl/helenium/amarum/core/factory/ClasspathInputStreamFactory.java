package pl.helenium.amarum.core.factory;

import pl.helenium.amarum.api.exception.FactoryException;

import java.io.InputStream;

import static org.apache.commons.lang3.Validate.notNull;

public class ClasspathInputStreamFactory extends AbstractFactory<InputStream> {

    private final String location;

    public ClasspathInputStreamFactory(String location) {
        this.location = notNull(location, "Location mustn't be null!");
    }

    @Override
    protected InputStream doProduce() throws FactoryException {
        return notNull(
                this.getClass().getResourceAsStream(this.location),
                "No resource at classpath:%s",
                this.location);
    }

}
