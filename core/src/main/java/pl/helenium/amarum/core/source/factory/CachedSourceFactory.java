package pl.helenium.amarum.core.source.factory;

import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.helenium.amarum.api.Source;

import java.util.Map;

public class CachedSourceFactory extends AbstractInMemorySourceFactory {

    private static final Logger log = LoggerFactory.getLogger(CachedSourceFactory.class);

    private final Source backingSource;

    public CachedSourceFactory(Source backingSource) {
        Validate.notNull(backingSource, "backingSource mustn't be null!");
        this.backingSource = backingSource;
    }

    @Override
    protected void fillEntries(Map<String, String> entries) {
        entries.putAll(this.backingSource.getAll());
    }

}
