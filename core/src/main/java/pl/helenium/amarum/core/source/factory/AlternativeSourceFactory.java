package pl.helenium.amarum.core.source.factory;

import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.helenium.amarum.api.Source;
import pl.helenium.amarum.api.SourceCreationException;
import pl.helenium.amarum.api.SourceFactory;

import java.util.Arrays;

public class AlternativeSourceFactory extends AbstractSourceFactory {

    private static final Logger log = LoggerFactory.getLogger(AlternativeSourceFactory.class);

    private final SourceFactory[] factories;

    public AlternativeSourceFactory(SourceFactory... factories) {
        Validate.notEmpty(factories);
        Validate.noNullElements(factories);
        this.factories = factories;
        log.info("Creating {} with SourceFactories: {}", this.getClass().getSimpleName(), Arrays.toString(factories));
    }

    @Override
    public Source doCreateSource() throws SourceCreationException {
        for (SourceFactory factory : factories) {
            try {
                log.debug("Trying to create source using SourceFactory: {}", factory);

                final Source source = factory.createSource();
                log.info("Source {} created with SourceFactory {}", source, factory);
                return source;
            } catch (Exception e) {
                log.info(String.format("Unable to create source using SourceFactory: %s. Will try next factory if available!", factory), e);
            }
        }

        throw new SourceCreationException("Unable to create source! All source factories have failed!");
    }

}
