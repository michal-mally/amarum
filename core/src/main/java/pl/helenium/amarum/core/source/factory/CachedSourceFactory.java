package pl.helenium.amarum.core.source.factory;

import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.helenium.amarum.api.Source;
import pl.helenium.amarum.core.source.InMemorySource;

public class CachedSourceFactory extends AbstractSourceFactory {

    private static final Logger log = LoggerFactory.getLogger(CachedSourceFactory.class);

    private final Source backingSource;

    public CachedSourceFactory(Source backingSource) {
        Validate.notNull(backingSource, "backingSource mustn't be null!");
        this.backingSource = backingSource;
    }

    @Override
    public Source doCreateSource() {
        return new InMemorySource(this.backingSource.getAll());
    }

}
