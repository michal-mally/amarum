package pl.helenium.amarum.core.factory.properties;

import pl.helenium.amarum.api.exception.FactoryException;
import pl.helenium.amarum.core.factory.AbstractFactory;

import java.util.Properties;

public class SystemPropertiesFactory extends AbstractFactory<Properties> {

    @Override
    protected Properties doProduce() throws FactoryException {
        return System.getProperties();
    }

}
