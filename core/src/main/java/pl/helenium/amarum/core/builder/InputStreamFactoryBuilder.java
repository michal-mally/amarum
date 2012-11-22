package pl.helenium.amarum.core.builder;

import pl.helenium.amarum.api.Factory;
import pl.helenium.amarum.api.exception.BuildException;
import pl.helenium.amarum.core.factory.stream.input.ClasspathInputStreamFactory;
import pl.helenium.amarum.core.factory.stream.input.FileInputStreamFactory;

import java.io.InputStream;

public class InputStreamFactoryBuilder extends AbstractBuilder<Factory<InputStream>, InputStreamFactoryBuilder> {

    public Factory<InputStream> fromClasspath(String location) throws BuildException {
        return configure(new ClasspathInputStreamFactory(location));
    }

    public Factory<InputStream> fromFile(String location) throws BuildException {
        return configure(new FileInputStreamFactory(location));
    }

    @Override
    protected InputStreamFactoryBuilder getThis() {
        return this;
    }

}
