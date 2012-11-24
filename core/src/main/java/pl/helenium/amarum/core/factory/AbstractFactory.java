package pl.helenium.amarum.core.factory;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.helenium.amarum.api.exception.FactoryException;
import pl.helenium.amarum.api.factory.Factory;

import static org.apache.commons.lang3.Validate.notNull;

public abstract class AbstractFactory<T> implements Factory<T> {

    @SuppressWarnings("unused")
    private static final Logger log = LoggerFactory.getLogger(AbstractFactory.class);

    private final boolean allowNullProduct;

    protected AbstractFactory(boolean allowNullProduct) {
        log.info("Creating {}", this.getClass().getName());
        this.allowNullProduct = allowNullProduct;
    }

    protected AbstractFactory() {
        this(false);
    }

    @Override
    public final T produce() throws FactoryException {
        log.debug("In {}.produce()", this.getClass().getSimpleName());
        try {
            final T product = doProduce();
            log.debug("Object produced: {}", product);
            if (!allowNullProduct) {
                notNull(product, "Factory does not allow null product!");
            }

            return product;
        } catch (RuntimeException e) {
            throw new FactoryException("Unable to create product!", e);
        }
    }

    protected abstract T doProduce() throws FactoryException;

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

}
