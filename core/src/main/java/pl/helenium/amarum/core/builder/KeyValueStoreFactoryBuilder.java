package pl.helenium.amarum.core.builder;

import pl.helenium.amarum.api.exception.BuildException;
import pl.helenium.amarum.api.factory.Factory;
import pl.helenium.amarum.api.store.KeyValueStore;
import pl.helenium.amarum.core.factory.store.FilterKeyValueStoreFactory;
import pl.helenium.amarum.core.factory.store.MergeKeyValueStoreFactory;
import pl.helenium.amarum.core.factory.store.PropertiesKeyValueStoreFactory;

import java.util.Properties;
import java.util.regex.Pattern;

public class KeyValueStoreFactoryBuilder extends AbstractBuilder<KeyValueStoreFactoryBuilder>{

    public Factory<KeyValueStore> fromPropertiesFactory(Factory<? extends Properties> factory) throws BuildException {
        return configure(new PropertiesKeyValueStoreFactory(factory));
    }

    public Factory<KeyValueStore> filter(Factory<? extends KeyValueStore> factory, Pattern... patterns) throws BuildException {
        return configure(new FilterKeyValueStoreFactory(factory, patterns));
    }

    public Factory<KeyValueStore> merge(Factory<? extends KeyValueStore>... factories) throws BuildException {
        return configure(new MergeKeyValueStoreFactory(factories));
    }

    @Override
    protected KeyValueStoreFactoryBuilder getThis() {
        return this;
    }

}
