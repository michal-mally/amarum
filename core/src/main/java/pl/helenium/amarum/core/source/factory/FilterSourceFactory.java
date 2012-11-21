package pl.helenium.amarum.core.source.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.helenium.amarum.api.Factory;
import pl.helenium.amarum.api.Source;

import java.util.Map;
import java.util.regex.Pattern;

import static org.apache.commons.lang3.Validate.*;

public class FilterSourceFactory extends AbstractInMemorySourceFactory {

    private static final Logger log = LoggerFactory.getLogger(FilterSourceFactory.class);

    private final Factory<Source> factory;

    private final Pattern[] patterns;

    public FilterSourceFactory(Factory<Source> factory, Pattern... patterns) {
        this.factory = notNull(factory);
        this.patterns = noNullElements(notEmpty(patterns));
    }

    @Override
    protected void fillEntries(Map<String, String> entries) throws Exception {
        final Source source = this.factory.produce();
        for (final Map.Entry<String, String> entry : source.getAll().entrySet()) {
            for (final Pattern pattern : this.patterns) {
                if (pattern.matcher(entry.getKey()).matches()) {
                    log.debug("Key {} matches pattern {} - adding to the map.", entry.getKey(), pattern);
                    entries.put(entry.getKey(), entry.getValue());
                    break;
                }
            }
        }
    }

}
