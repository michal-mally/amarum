package pl.helenium.amarum.core.source.factory;

import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.helenium.amarum.api.Factory;
import pl.helenium.amarum.api.Source;

import java.util.Map;

public class MergedSourceFactory extends AbstractInMemorySourceFactory {

    private static final Logger log = LoggerFactory.getLogger(MergedSourceFactory.class);

    private final Factory<Source>[] factories;

    public MergedSourceFactory(Factory<Source>... factories) {
        Validate.notNull(factories);
        Validate.noNullElements(factories);
        this.factories = factories;
    }

    @Override
    protected void fillEntries(Map<String, String> entries) throws Exception {
        for (Factory<Source> factory : factories) {
            final Map<String, String> factoryEntries = factory.produce().getAll();
            log.debug("Entries from Factory {}: {}", factory, factoryEntries);
            entries.putAll(factoryEntries);
        }
    }

}