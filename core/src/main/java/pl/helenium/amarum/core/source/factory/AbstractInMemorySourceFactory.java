package pl.helenium.amarum.core.source.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.helenium.amarum.api.Source;
import pl.helenium.amarum.api.exception.FactoryException;
import pl.helenium.amarum.core.factory.AbstractFactory;
import pl.helenium.amarum.core.source.InMemorySource;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractInMemorySourceFactory extends AbstractFactory<Source> {

    private static final Logger log = LoggerFactory.getLogger(AbstractInMemorySourceFactory.class);

    @Override
    protected final Source doProduce() throws FactoryException {
        try {
            log.debug("Delegating creation of entries to {}.fillEntries()", this.getClass().getSimpleName());
            final Map<String, String> entries = new HashMap<String, String>();
            fillEntries(entries);
            log.info("Entries returned by fillEntries(): {}", entries);
            return new InMemorySource(entries);
        } catch (Exception e) {
            throw new FactoryException("Unable to produce object!", e);
        }
    }

    protected abstract void fillEntries(Map<String,String> entries) throws Exception;

}
