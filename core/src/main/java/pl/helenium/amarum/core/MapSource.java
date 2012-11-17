package pl.helenium.amarum.core;

import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.helenium.amarum.api.Source;
import sun.java2d.pipe.ValidatePipe;

import java.util.HashMap;
import java.util.Map;

import static java.util.Collections.unmodifiableMap;

public class MapSource implements Source {

    private static final Logger log = LoggerFactory.getLogger(MapSource.class);

    protected final Map<String, String> map;

    public MapSource(Map<String, String> map) {
        Validate.notNull(map, "map parameter mustn't be null");
        this.map = map;
    }

    public MapSource() {
        this(new HashMap<String, String>());
    }

    @Override
    public String get(String key) {
        return this.map.get(key);
    }

    @Override
    public Map<String, String> getAll() {
        return unmodifiableMap(this.map);
    }

}
