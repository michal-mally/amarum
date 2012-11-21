package pl.helenium.amarum.core.source;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

import static java.util.Collections.unmodifiableMap;
import static org.apache.commons.lang3.Validate.notNull;

public class InMemorySource extends AbstractSource {

    private static final Logger log = LoggerFactory.getLogger(InMemorySource.class);

    protected final Map<String, String> map;

    public InMemorySource(Map<String, String> map) {
        this.map = notNull(map, "map parameter mustn't be null");
    }

    public InMemorySource() {
        this(new HashMap<String, String>());
    }

    @Override
    public Map<String, String> getAll() {
        return unmodifiableMap(this.map);
    }

}
