package pl.helenium.amarum.core.factory.store;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.helenium.amarum.api.factory.Factory;
import pl.helenium.amarum.api.store.KeyValueStore;

import java.util.Map;

import static org.apache.commons.lang3.Validate.noNullElements;
import static org.apache.commons.lang3.Validate.notNull;
import static pl.helenium.amarum.core.store.KeyValueStoreUtils.asMap;

public class MergedKeyValueStoreFactory extends AbstractInMemoryKeyValueStoreFactory {

    @SuppressWarnings("unused")
    private static final Logger log = LoggerFactory.getLogger(MergedKeyValueStoreFactory.class);

    private final Factory<KeyValueStore>[] factories;

    public MergedKeyValueStoreFactory(Factory<KeyValueStore>... factories) {
        this.factories = noNullElements(notNull(factories));
    }

    @Override
    protected void fillEntries(Map<String, String> entries) throws Exception {
        for (Factory<KeyValueStore> factory : factories) {
            entries.putAll(asMap(factory.produce()));
        }
    }

}
