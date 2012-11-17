package pl.helenium.amarum.core.factory;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.helenium.amarum.api.Factory;
import pl.helenium.amarum.api.FactoryException;

public abstract class AbstractFactory<T> implements Factory<T> {

    private static final Logger log = LoggerFactory.getLogger(AbstractFactory.class);

    protected AbstractFactory() {
        log.info("Creating {}", this.getClass().getName());
    }

    @Override
    public final T produce() throws FactoryException {
        log.debug("In {}.produce()", this.getClass().getSimpleName());
        try {
            final T product = doProduce();
            log.debug("Object produced: {}", product);
            return product;
        } catch (RuntimeException e) {
            throw new FactoryException("Unable to produce object!", e);
        }
    }

    protected abstract T doProduce() throws FactoryException;

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

}
