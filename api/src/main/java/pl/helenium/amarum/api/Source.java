package pl.helenium.amarum.api;

import java.util.Map;

public interface Source {

    String get(String key);

    Map<String, String> getAll();

}
