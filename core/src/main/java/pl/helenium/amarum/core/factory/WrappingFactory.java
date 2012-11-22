package pl.helenium.amarum.core.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.helenium.amarum.api.exception.FactoryException;

public class WrappingFactory<T> extends AbstractFactory<T> {

    private static final Logger log = LoggerFactory.getLogger(WrappingFactory.class);

    private final T product;

    public WrappingFactory(T product) {
        super(true);
        this.product = product;
        if (product == null) {
            log.warn("Product is null!");
        }
    }

    @Override
    protected T doProduce() throws FactoryException {
        return this.product;
    }

}
