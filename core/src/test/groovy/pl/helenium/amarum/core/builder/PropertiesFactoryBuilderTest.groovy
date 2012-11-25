package pl.helenium.amarum.core.builder

import org.testng.annotations.Test

import static pl.helenium.amarum.core.builder.Builders.build
import pl.helenium.amarum.api.exception.BuildException

class PropertiesFactoryBuilderTest extends AbstractBuildersTest {

    @Test
    void shallConstructPropertiesFactoryFromInputStreamFactory() {
        // given
        def content = """x=y
z=a
        """
        def is = build().factory().fromProduct(new ByteArrayInputStream(content.bytes))

        // when
        def factory = build().propertiesFactory().fromInputStreamFactory(is)

        // then
        assert factory.produce() == [x: "y", z: "a"]
    }

    @Test
    void shallConstructPropertiesFactoryFromSystemProperties() {
        // given

        // when
        def factory = build().propertiesFactory().fromSystem()

        // then
        assert factory.produce() == System.properties
    }



}
