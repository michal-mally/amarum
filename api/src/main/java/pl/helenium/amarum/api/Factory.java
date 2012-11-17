package pl.helenium.amarum.api;

public interface Factory<T> {

    T produce() throws FactoryException;

}
