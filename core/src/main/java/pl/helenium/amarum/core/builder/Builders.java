package pl.helenium.amarum.core.builder;

public final class Builders {

    public static InputStreamFactoryBuilder inputStreamFactory() {
        return new InputStreamFactoryBuilder();
    }

    public static PropertiesFactoryBuilder propertiesFactory() {
        return new PropertiesFactoryBuilder();
    }

    private Builders() {
        // intentionally left blank
    }

}
