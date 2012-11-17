package pl.helenium.amarum.core.factory;

import org.apache.commons.lang3.Validate;
import pl.helenium.amarum.api.FactoryException;

public class WrappingFactory<T> extends AbstractFactory<T> {

    private final T object;

    public WrappingFactory(T object) {
        Validate.notNull(object);
        this.object = object;
    }

    @Override
    protected T doProduce() throws FactoryException {
        return this.object;
    }

}
