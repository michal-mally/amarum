package pl.helenium.amarum.core.factory.store;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.helenium.amarum.api.exception.FactoryException;
import pl.helenium.amarum.api.store.KeyValueStore;
import pl.helenium.amarum.core.factory.AbstractFactory;
import pl.helenium.amarum.core.store.InMemoryKeyValueStore;

import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public abstract class AbstractInMemoryKeyValueStoreFactory extends AbstractFactory<KeyValueStore> {

    @SuppressWarnings("unused")
    private static final Logger log = LoggerFactory.getLogger(AbstractInMemoryKeyValueStoreFactory.class);

    @Override
    protected final KeyValueStore doProduce() throws FactoryException {
        try {
            log.debug("Delegating creation of entries to {}.fillEntries()", this.getClass().getSimpleName());
            final SortedMap<String, String> entries = new TreeMap<String, String>();
            fillEntries(entries);
            log.info("Entries returned by fillEntries(): {}", entries);
            return new InMemoryKeyValueStore(entries);
        } catch (Exception e) {
            throw new FactoryException("Unable to produce object!", e);
        }
    }

    protected abstract void fillEntries(Map<String,String> entries) throws Exception;

}
