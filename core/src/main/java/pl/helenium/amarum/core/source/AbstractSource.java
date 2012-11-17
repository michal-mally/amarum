package pl.helenium.amarum.core.source;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.helenium.amarum.api.Source;

public abstract class AbstractSource implements Source {

    private static final Logger log = LoggerFactory.getLogger(AbstractSource.class);

    protected AbstractSource() {
        log.info("Creating {}", this.getClass().getName());
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

}
