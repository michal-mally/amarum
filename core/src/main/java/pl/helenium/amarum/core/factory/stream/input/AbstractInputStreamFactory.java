package pl.helenium.amarum.core.factory.stream.input;

import pl.helenium.amarum.api.exception.FactoryException;
import pl.helenium.amarum.core.factory.AbstractFactory;

import java.io.InputStream;

import static org.apache.commons.lang3.Validate.notNull;

public abstract class AbstractInputStreamFactory extends AbstractFactory<InputStream> {

    private final String location;

    public AbstractInputStreamFactory(String location) {
        this.location = notNull(location, "Location mustn't be null!");
    }

    @Override
    protected InputStream doProduce() throws FactoryException {
        try {
            return notNull(
                getInputStream(),
                "No resource at %s",
                this.location);
        } catch (Exception e) {
            throw new FactoryException(String.format("Unable to obtain input stream from location %s", this.location), e);
        }
    }

    protected String getLocation() {
        return this.location;
    }

    protected abstract InputStream getInputStream() throws Exception;

}
