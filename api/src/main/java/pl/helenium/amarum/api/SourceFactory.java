package pl.helenium.amarum.api;

public interface SourceFactory {

    Source createSource() throws SourceCreationException;

}
