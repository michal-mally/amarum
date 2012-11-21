package pl.helenium.amarum.core.source.factory;

import pl.helenium.amarum.api.Factory;
import pl.helenium.amarum.api.Refreshable;
import pl.helenium.amarum.api.Source;
import pl.helenium.amarum.api.exception.RefreshException;
import pl.helenium.amarum.core.source.AbstractSource;

import java.util.Map;

import static org.apache.commons.lang3.Validate.notNull;

public class RefreshableSource extends AbstractSource implements Refreshable {

    private final Factory<Source> factory;

    private Source source;

    public RefreshableSource(Factory<Source> factory) throws RefreshException {
        this.factory = notNull(factory, "Factory mustn't be null");
        refresh();
    }

    @Override
    public Map<String, String> getAll() {
        return this.source.getAll();
    }

    @Override
    public final void refresh() throws RefreshException {
        try {
            this.source = notNull(factory.produce(), "Source produced by Factory is null!");
        } catch (Exception e) {
            throw new RefreshException("Unable to refresh (reproduce) source!", e);
        }
    }

}
