package pl.helenium.amarum.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.helenium.amarum.api.Source;

import java.util.Map;

public class CachingSource extends MapSource {

    private static final Logger log = LoggerFactory.getLogger(CachingSource.class);

    private final Source source;

    private boolean initialized = false;

    public CachingSource(Source source) {
        super();
        this.source = source;
    }

    @Override
    public String get(String key) {
        init();
        return super.get(key);
    }

    @Override
    public Map<String, String> getAll() {
        init();
        return super.getAll();
    }

    private void init() {
        if(this.initialized) {
            return;
        }

        log.info("Initializing CachingSource using Source: {}", this.source);
        final Map<String, String> entries = this.source.getAll();
        for (Map.Entry<String, String> entry : entries.entrySet()) {
            log.info("Mapping {} -> {}", entry.getKey(), entry.getValue());
            this.map.put(entry.getKey(), entry.getValue());
        }
        this.initialized = true;
    }

}
