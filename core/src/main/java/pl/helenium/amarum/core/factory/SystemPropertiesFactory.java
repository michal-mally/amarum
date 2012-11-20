package pl.helenium.amarum.core.factory;

import pl.helenium.amarum.api.FactoryException;

import java.util.Properties;

public class SystemPropertiesFactory extends AbstractFactory<Properties> {

    @Override
    protected Properties doProduce() throws FactoryException {
        return System.getProperties();
    }

}
