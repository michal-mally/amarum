package pl.helenium.amarum.core.factory.source;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.helenium.amarum.api.Source;

import java.util.Map;

import static org.apache.commons.lang3.Validate.notNull;

public class CachedSourceFactory extends AbstractInMemorySourceFactory {

    private static final Logger log = LoggerFactory.getLogger(CachedSourceFactory.class);

    private final Source backingSource;

    public CachedSourceFactory(Source backingSource) {
        this.backingSource = notNull(backingSource, "backingSource mustn't be null!");
    }

    @Override
    protected void fillEntries(Map<String, String> entries) {
        entries.putAll(this.backingSource.getAll());
    }

}