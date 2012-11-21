package pl.helenium.amarum.api;

import pl.helenium.amarum.api.exception.RefreshException;

public interface Refreshable {

    void refresh() throws RefreshException;

}
