package pl.helenium.amarum.core.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.helenium.amarum.api.Factory;
import pl.helenium.amarum.api.exception.FactoryException;

import java.util.Arrays;

import static org.apache.commons.lang3.Validate.noNullElements;
import static org.apache.commons.lang3.Validate.notEmpty;

public class AlternativeFactory<T> extends AbstractFactory<T> {

    private static final Logger log = LoggerFactory.getLogger(AlternativeFactory.class);

    private final Factory<T>[] factories;

    public AlternativeFactory(Factory<T>... factories) {
        notEmpty(factories);
        noNullElements(factories);
        this.factories = factories;
        log.info("Creating {} with Factories: {}", this.getClass().getSimpleName(), Arrays.toString(factories));
    }

    @Override
    public T doProduce() throws FactoryException {
        for (Factory<T> factory : factories) {
            try {
                log.debug("Trying to produce object using Factory: {}", factory);

                final T object = factory.produce();
                log.info("Object {} created with Factory {}", object, factory);
                return object;
            } catch (Exception e) {
                log.info(String.format("Unable to produce object using Factory: %s. Will try next factory if available!", factory), e);
            }
        }

        throw new FactoryException("Unable to produce object! All factories have failed!");
    }

}
