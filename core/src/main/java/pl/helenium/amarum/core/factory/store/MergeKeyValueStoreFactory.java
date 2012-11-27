package pl.helenium.amarum.core.factory.store;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.helenium.amarum.api.exception.FactoryException;
import pl.helenium.amarum.api.factory.Factory;
import pl.helenium.amarum.api.store.KeyValueStore;
import pl.helenium.amarum.core.factory.AbstractFactory;
import pl.helenium.amarum.core.store.MergeKeyValueStore;

import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang3.Validate.noNullElements;
import static org.apache.commons.lang3.Validate.notNull;

public class MergeKeyValueStoreFactory extends AbstractFactory<KeyValueStore> {

    @SuppressWarnings("unused")
    private static final Logger log = LoggerFactory.getLogger(MergeKeyValueStoreFactory.class);

    private final Factory<? extends KeyValueStore>[] factories;

    public MergeKeyValueStoreFactory(Factory<? extends KeyValueStore>... factories) {
        this.factories = noNullElements(notNull(factories));
    }

    @Override
    protected KeyValueStore doProduce() throws FactoryException {
        final List<KeyValueStore> backingStores = new ArrayList<KeyValueStore>();
        for (final Factory<? extends KeyValueStore> factory : factories) {
            backingStores.add(factory.produce());
        }
        return new MergeKeyValueStore(backingStores.toArray(new KeyValueStore[backingStores.size()]));
    }
}
