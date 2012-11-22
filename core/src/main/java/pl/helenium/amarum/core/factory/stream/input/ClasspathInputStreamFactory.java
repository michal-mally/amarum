package pl.helenium.amarum.core.factory.stream.input;

import java.io.InputStream;

public class ClasspathInputStreamFactory extends AbstractInputStreamFactory {

    public ClasspathInputStreamFactory(String location) {
        super(location);
    }

    protected InputStream getInputStream() {
        return this.getClass().getResourceAsStream(getLocation());
    }

}
