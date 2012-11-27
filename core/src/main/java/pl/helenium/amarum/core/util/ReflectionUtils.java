package pl.helenium.amarum.core.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;

import static java.lang.System.arraycopy;

public final class ReflectionUtils {

    @SuppressWarnings("unused")
    private static final Logger log = LoggerFactory.getLogger(ReflectionUtils.class);

    public static <T> Constructor<T> getAnnotatedConstructor(Class<T> clazz, Class<? extends Annotation> annotationClass) {
        final Constructor<T>[] constructors = (Constructor<T>[]) clazz.getConstructors();
        for (final Constructor<T> constructor : constructors) {
            if (constructor.getAnnotation(annotationClass) != null) {
                log.info("FactoryConstructor found: {}", constructor);
                return constructor;
            }
        }

        // TODO
        throw new RuntimeException(String.format("Class %s has no constructor annotated with %s!", clazz, annotationClass));
    }

    public static Object[] flattenVarargs(Object[] args) {
        if (args.length == 0) {
            return args;
        }

        final Object lastArg = args[args.length - 1];
        if (lastArg == null || !lastArg.getClass().isArray()) {
            return args;
        }

        final int argCount = args.length - 1 + Array.getLength(lastArg);
        final Object[] flattenArgs = new Object[argCount];
        arraycopy(args, 0, flattenArgs, 0, args.length - 1);
        for (int i = 0; i < Array.getLength(lastArg); i++) {
            flattenArgs[args.length - 1 + i] = Array.get(lastArg, i);
        }

        return flattenArgs;
    }

    public static Object[] deflattenVarargs(Object[] inputArgs, int paramCount, Class<?> varargClass) {
        final Object[] args = new Object[paramCount];
        arraycopy(inputArgs, 0, args, 0, paramCount - 1);
        final int varargCount = inputArgs.length - paramCount + 1;
        args[paramCount - 1] = Array.newInstance(varargClass, varargCount);
        for (int i = 0; i < varargCount; i++) {
            Array.set(args[paramCount - 1], i, inputArgs[paramCount - 1 + i]);
        }
        return args;
    }

    private ReflectionUtils() {
        // intentionally left blank
    }

}
