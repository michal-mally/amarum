package pl.helenium.amarum.core.builder;

import pl.helenium.amarum.api.exception.BuildException;

public final class Builders extends AbstractBuilder<Builders> {

    public static Builders build() {
        return new Builders();
    }

    public FactoryBuilder factory() throws BuildException {
        return configure(new FactoryBuilder());
    }

    public InputStreamFactoryBuilder inputStreamFactory() throws BuildException {
        return configure(new InputStreamFactoryBuilder());
    }

    public PropertiesFactoryBuilder propertiesFactory() throws BuildException {
        return configure(new PropertiesFactoryBuilder());
    }

    public KeyValueStoreFactoryBuilder keyValueStoreFactory() throws BuildException {
        return configure(new KeyValueStoreFactoryBuilder());
    }

    public KeyValueStoreBuilder keyValueStore() throws BuildException {
        return configure(new KeyValueStoreBuilder());
    }

    @Override
    protected Builders getThis() {
        return this;
    }

    private Builders() {
        // intentionally left blank
    }

}
