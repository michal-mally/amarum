package pl.helenium.amarum.core.factory.store;

import pl.helenium.amarum.api.exception.RefreshException;
import pl.helenium.amarum.api.factory.Factory;
import pl.helenium.amarum.api.store.KeyValueStore;
import pl.helenium.amarum.api.store.RefreshableKeyValueStore;
import pl.helenium.amarum.core.store.AbstractKeyValueStore;

import java.util.NavigableSet;

import static org.apache.commons.lang3.Validate.notNull;

public class DefaultRefreshableKeyValueStore extends AbstractKeyValueStore implements RefreshableKeyValueStore {

    private final Factory<KeyValueStore> factory;

    private KeyValueStore keyValueStore;

    public DefaultRefreshableKeyValueStore(Factory<KeyValueStore> factory) throws RefreshException {
        this.factory = notNull(factory, "Factory mustn't be null");
        refresh();
    }

    @Override
    public NavigableSet<String> getAllKeys() {
        return this.keyValueStore.getAllKeys();
    }

    @Override
    public String getValue(String key) {
        return this.keyValueStore.getValue(key);
    }

    @Override
    public final void refresh() throws RefreshException {
        try {
            this.keyValueStore = notNull(factory.produce(), "KeyValueStore produced by Factory mustn't be null!");
        } catch (Exception e) {
            throw new RefreshException("Unable to refresh (reproduce) keyValueStore!", e);
        }
    }

}
