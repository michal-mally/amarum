package pl.helenium.amarum.core.factory;

import pl.helenium.amarum.api.FactoryException;

import static org.apache.commons.lang3.Validate.notNull;

public class WrappingFactory<T> extends AbstractFactory<T> {

    private final T object;

    public WrappingFactory(T object) {
        notNull(object);
        this.object = object;
    }

    @Override
    protected T doProduce() throws FactoryException {
        return this.object;
    }

}
