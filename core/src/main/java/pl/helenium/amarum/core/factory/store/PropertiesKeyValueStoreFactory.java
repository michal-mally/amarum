package pl.helenium.amarum.core.factory.store;

import pl.helenium.amarum.api.exception.FactoryException;
import pl.helenium.amarum.api.factory.Factory;
import pl.helenium.amarum.api.store.KeyValueStore;
import pl.helenium.amarum.core.factory.AbstractFactory;
import pl.helenium.amarum.core.store.PropertiesKeyValueStore;

import java.util.Map;
import java.util.Properties;

import static org.apache.commons.lang3.Validate.notNull;

public class PropertiesKeyValueStoreFactory extends AbstractFactory<KeyValueStore> {

    private final Factory<? extends Properties> propertiesFactory;

    public PropertiesKeyValueStoreFactory(Factory<? extends Properties> propertiesFactory) {
        this.propertiesFactory = notNull(propertiesFactory);
    }

    @Override
    protected KeyValueStore doProduce() throws FactoryException {
        return new PropertiesKeyValueStore(this.propertiesFactory.produce());
    }
}
