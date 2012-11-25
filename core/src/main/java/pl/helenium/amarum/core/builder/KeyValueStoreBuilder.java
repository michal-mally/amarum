package pl.helenium.amarum.core.builder;

import pl.helenium.amarum.api.exception.BuildException;
import pl.helenium.amarum.api.store.KeyValueStore;
import pl.helenium.amarum.core.store.InMemoryKeyValueStore;

import java.util.Map;

public class KeyValueStoreBuilder extends AbstractBuilder<KeyValueStoreBuilder> {

    public KeyValueStore fromMap(Map<String, String> map) throws BuildException {
        return configure(new InMemoryKeyValueStore(map));
    }

    @Override
    protected KeyValueStoreBuilder getThis() {
        return this;
    }

}
