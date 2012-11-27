package pl.helenium.amarum.core.builder;

import pl.helenium.amarum.api.exception.BuildException;
import pl.helenium.amarum.api.factory.Factory;
import pl.helenium.amarum.api.store.KeyValueStore;
import pl.helenium.amarum.core.factory.store.ReflectiveKeyValueStoreFactory;
import pl.helenium.amarum.core.store.FilterKeyValueStore;
import pl.helenium.amarum.core.store.MergeKeyValueStore;
import pl.helenium.amarum.core.store.PropertiesKeyValueStore;

import java.util.Properties;
import java.util.regex.Pattern;

public class KeyValueStoreFactoryBuilder extends AbstractBuilder<KeyValueStoreFactoryBuilder>{

    public Factory<KeyValueStore> fromPropertiesFactory(Factory<? extends Properties> factory) throws BuildException {
        return configure(new ReflectiveKeyValueStoreFactory(PropertiesKeyValueStore.class, factory));
    }

    public Factory<KeyValueStore> filter(Factory<? extends KeyValueStore> factory, Pattern... patterns) throws BuildException {
        return configure(new ReflectiveKeyValueStoreFactory(FilterKeyValueStore.class, factory, patterns));
    }

    public Factory<KeyValueStore> merge(Factory<? extends KeyValueStore>... factories) throws BuildException {
        return configure(new ReflectiveKeyValueStoreFactory(MergeKeyValueStore.class, (Object[]) factories));
    }

    @Override
    protected KeyValueStoreFactoryBuilder getThis() {
        return this;
    }

}
