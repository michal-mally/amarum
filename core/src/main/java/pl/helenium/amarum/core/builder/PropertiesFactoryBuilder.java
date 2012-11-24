package pl.helenium.amarum.core.builder;

import pl.helenium.amarum.api.exception.BuildException;
import pl.helenium.amarum.api.factory.Factory;
import pl.helenium.amarum.core.factory.properties.InputStreamPropertiesFactory;
import pl.helenium.amarum.core.factory.properties.SystemPropertiesFactory;

import java.io.InputStream;
import java.util.Properties;

public class PropertiesFactoryBuilder extends AbstractBuilder<Factory<Properties>, PropertiesFactoryBuilder>{

    public Factory<Properties> fromSystem() throws BuildException {
        return configure(new SystemPropertiesFactory());
    }

    public Factory<Properties> fromInputStreamFactory(Factory<InputStream> factory) throws BuildException {
        return configure(new InputStreamPropertiesFactory(factory));
    }

    @Override
    protected PropertiesFactoryBuilder getThis() {
        return this;
    }

}
