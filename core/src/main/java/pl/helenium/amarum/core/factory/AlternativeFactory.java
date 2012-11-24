package pl.helenium.amarum.core.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.helenium.amarum.api.exception.FactoryException;
import pl.helenium.amarum.api.factory.Factory;

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
                log.debug("Trying to create product using Factory: {}", factory);

                final T product = factory.produce();
                log.info("Product {} created with Factory {}", product, factory);
                return product;
            } catch (Exception e) {
                log.info(String.format("Unable to create product using Factory: %s. Will try next factory if available!", factory), e);
            }
        }

        throw new FactoryException("Unable to create product! All factories have failed!");
    }

}
