package pl.helenium.amarum.core.builder;

import pl.helenium.amarum.api.exception.BuildException;
import pl.helenium.amarum.api.factory.Factory;
import pl.helenium.amarum.core.factory.AlternativeFactory;
import pl.helenium.amarum.core.factory.WrappingFactory;

public class FactoryBuilder extends AbstractBuilder<FactoryBuilder> {

    public <T> Factory<T> fromAlternative(Factory<T>... factories) throws BuildException {
        return configure(new AlternativeFactory<T>(factories));
    }

    public <T> Factory<T> fromProduct(T product) throws BuildException {
        return configure(new WrappingFactory<T>(product));
    }

    @Override
    protected FactoryBuilder getThis() {
        return this;
    }

}
