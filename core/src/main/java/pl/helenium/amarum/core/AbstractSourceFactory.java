package pl.helenium.amarum.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.helenium.amarum.api.Source;
import pl.helenium.amarum.api.SourceCreationException;
import pl.helenium.amarum.api.SourceFactory;

public abstract class AbstractSourceFactory implements SourceFactory {

    private static final Logger log = LoggerFactory.getLogger(AbstractSourceFactory.class);

    @Override
    public final Source createSource() throws SourceCreationException {
        log.debug("In createSource() of {}", this);
        try {
            final Source source = doCreateSource();
            log.debug("Source created: {}", source);
            return source;
        } catch (RuntimeException e) {
            throw new SourceCreationException("Unable to create source!", e);
        }
    }

    protected abstract Source doCreateSource() throws SourceCreationException;

}
