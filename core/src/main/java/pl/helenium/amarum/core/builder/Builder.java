package pl.helenium.amarum.core.builder;

public interface Builder<T extends Builder> {

    T with(String property, Object value);

}
