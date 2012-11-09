package pl.helenium.amarum.api;

public interface Configuration {

    <T> T getValue(Class<T> valueClass);

    <T> T getValue(Class<T> valueClass, T defaultValue);

}
