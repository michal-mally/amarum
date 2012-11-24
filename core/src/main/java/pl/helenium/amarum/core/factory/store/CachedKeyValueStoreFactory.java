package pl.helenium.amarum.core.factory.store;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.helenium.amarum.api.store.KeyValueStore;

import java.util.Map;

import static org.apache.commons.lang3.Validate.notNull;
import static pl.helenium.amarum.core.store.KeyValueStoreUtils.asMap;

public class CachedKeyValueStoreFactory extends AbstractInMemoryKeyValueStoreFactory {

    private static final Logger log = LoggerFactory.getLogger(CachedKeyValueStoreFactory.class);

    private final KeyValueStore backingKeyValueStore;

    public CachedKeyValueStoreFactory(KeyValueStore backingKeyValueStore) {
        this.backingKeyValueStore = notNull(backingKeyValueStore, "backingKeyValueStore mustn't be null!");
    }

    @Override
    protected void fillEntries(Map<String, String> entries) {
        entries.putAll(asMap(backingKeyValueStore));
    }

}
