package pl.helenium.amarum.core.factory.store;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.helenium.amarum.api.exception.FactoryException;
import pl.helenium.amarum.api.factory.Factory;
import pl.helenium.amarum.api.store.KeyValueStore;
import pl.helenium.amarum.core.annotation.FactoryConstructor;
import pl.helenium.amarum.core.factory.AbstractFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static java.util.Arrays.asList;
import static org.apache.commons.lang3.Validate.notNull;
import static pl.helenium.amarum.core.util.ReflectionUtils.*;

public class ReflectiveKeyValueStoreFactory extends AbstractFactory<KeyValueStore> {

    @SuppressWarnings("unused")
    private static final Logger log = LoggerFactory.getLogger(ReflectiveKeyValueStoreFactory.class);

    private final Class<? extends KeyValueStore> clazz;

    private final Object[] rawArgs;

    public ReflectiveKeyValueStoreFactory(Class<? extends KeyValueStore> clazz, Object... rawArgs) {
        this.clazz = notNull(clazz);
        this.rawArgs = notNull(rawArgs);
    }

    @Override
    protected KeyValueStore doProduce() throws FactoryException {
        Object[] args = null;
        try {
            final Constructor<? extends KeyValueStore> factoryConstructor = getAnnotatedConstructor(this.clazz, FactoryConstructor.class);
            args = transformArgs(factoryConstructor);
            return factoryConstructor.newInstance((Object[]) args);
        } catch (InstantiationException e) {
            throw new FactoryException(String.format("Unable to produce %s reflectively with rawArgs: %s", this.clazz, asList(args)), e);
        } catch (IllegalAccessException e) {
            throw new FactoryException(String.format("Unable to produce %s reflectively with rawArgs: %s", this.clazz, asList(args)), e);
        } catch (InvocationTargetException e) {
            throw new FactoryException(String.format("Unable to produce %s reflectively with rawArgs: %s", this.clazz, asList(args)), e);
        }
    }

    private Object[] transformArgs(Constructor<? extends KeyValueStore> constructor) throws FactoryException {
        final Object[] flattenArgs = flattenVarargs(this.rawArgs);
        final Object[] transformedArgs = new Object[flattenArgs.length];
        for (int i = 0; i < transformedArgs.length; i++) {
            transformedArgs[i] = (flattenArgs[i] instanceof Factory) ? Factory.class.cast(flattenArgs[i]).produce() : flattenArgs[i];
        }
        if (!constructor.isVarArgs()) {
            return transformedArgs;
        }

        final Class<?>[] parameterTypes = constructor.getParameterTypes();
        return deflattenVarargs(transformedArgs, parameterTypes.length, parameterTypes[parameterTypes.length - 1].getComponentType());
    }

}
