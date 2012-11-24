package pl.helenium.amarum.api.factory;

import pl.helenium.amarum.api.exception.FactoryException;

public interface Factory<T> {

    T produce() throws FactoryException;

}
