package pl.helenium.amarum.core.factory.store;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.helenium.amarum.api.exception.FactoryException;
import pl.helenium.amarum.api.factory.Factory;
import pl.helenium.amarum.api.store.KeyValueStore;
import pl.helenium.amarum.core.factory.AbstractFactory;
import pl.helenium.amarum.core.store.FilterKeyValueStore;

import java.util.regex.Pattern;

import static org.apache.commons.lang3.Validate.*;

public class FilterKeyValueStoreFactory extends AbstractFactory<KeyValueStore> {

    @SuppressWarnings("unused")
    private static final Logger log = LoggerFactory.getLogger(FilterKeyValueStoreFactory.class);

    private final Factory<? extends KeyValueStore> factory;

    private final Pattern[] patterns;

    public FilterKeyValueStoreFactory(Factory<? extends KeyValueStore> factory, Pattern... patterns) {
        this.factory = notNull(factory);
        this.patterns = noNullElements(notEmpty(patterns));
    }

    @Override
    protected KeyValueStore doProduce() throws FactoryException {
        return new FilterKeyValueStore(factory.produce(), this.patterns);
    }

}
