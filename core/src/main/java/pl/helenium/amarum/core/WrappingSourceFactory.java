package pl.helenium.amarum.core;

import org.apache.commons.lang3.Validate;
import pl.helenium.amarum.api.Source;
import pl.helenium.amarum.api.SourceCreationException;

public class WrappingSourceFactory extends AbstractSourceFactory {

    private final Source source;

    public WrappingSourceFactory(Source source) {
        Validate.notNull(source);
        this.source = source;
    }

    @Override
    protected Source doCreateSource() throws SourceCreationException {
        return this.source;
    }

}
