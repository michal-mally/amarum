package pl.helenium.amarum.core.store;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.helenium.amarum.api.store.KeyValueStore;

public abstract class AbstractKeyValueStore implements KeyValueStore {

    private static final Logger log = LoggerFactory.getLogger(AbstractKeyValueStore.class);

    protected AbstractKeyValueStore() {
        log.info("Creating {}", this.getClass().getName());
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

}
