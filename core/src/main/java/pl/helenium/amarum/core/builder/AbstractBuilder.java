package pl.helenium.amarum.core.builder;

import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.helenium.amarum.api.exception.BuildException;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import static org.apache.commons.lang3.Validate.notNull;

public abstract class AbstractBuilder<B extends AbstractBuilder> implements Builder<B> {

    @SuppressWarnings("unused")
    private static final Logger log = LoggerFactory.getLogger(AbstractBuilder.class);

    private final Map<String, Object> extraProperties = new HashMap<String, Object>();

    private boolean ignoreNonExistingProperties = false;

    @Override
    public B with(String property, Object value) {
        log.info("Setting extra property {} -> {}", property, value);
        extraProperties.put(property, value);
        return getThis();
    }

    public B ignoreNonExistingProperties() {
        this.ignoreNonExistingProperties = true;
        return getThis();
    }

    protected abstract B getThis();

    protected <T> T configure(T product) throws BuildException {
        try {
            notNull(product, "Constructed object can't be null!");
            setExtraProperties(product);
            return product;
        } catch (Exception e) {
            throw new BuildException("Unable to configure object!", e);
        }
    }

    private void setExtraProperties(Object product) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        for (Map.Entry<String, Object> property : extraProperties.entrySet()) {
            setExtraProperty(product, property.getKey(), property.getValue());
        }
    }

    private void setExtraProperty(Object product, String propertyName, Object value) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        log.debug("Setting extra property {} -> {} @ {}", propertyName, value, product);
        try {
            PropertyUtils.setProperty(product, propertyName, value);
        } catch (NoSuchMethodException e) {
            log.info("No property {} in object {}; ignoreNonExistingProperties flag set to {}.", propertyName, product, this.ignoreNonExistingProperties);
            if (this.ignoreNonExistingProperties) {
                log.info("Ignoring non-existing property {} @ {}", propertyName, product);
                return;
            }

            log.warn("Raising exception due to non-existing property {} @ {}", propertyName, product);
            throw e;
        }
    }

}
