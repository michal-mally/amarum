package pl.helenium.amarum.core.factory.properties

import org.testng.annotations.Test
import pl.helenium.amarum.core.factory.WrappingFactory
import pl.helenium.amarum.core.factory.stream.input.ClasspathInputStreamFactory

class InputStreamPropertiesFactoryTest {

    @Test(expectedExceptions = NullPointerException.class)
    void shallFailWhenNullPassedAsFactory() {
        // given
        def isFactory = null

        // when
        def factory = new InputStreamPropertiesFactory(isFactory)

        // then
        // exception expected
    }

    @Test(expectedExceptions = NullPointerException.class)
    void shallFailWhenFactoryProducesNullInputStream() {
        // given
        def isFactory = new WrappingFactory<InputStream>(null)
        def factory = new InputStreamPropertiesFactory(isFactory)

        // when
        factory.doProduce()

        // then
        // exception expected
    }

    @Test
    void shallConstructPropertiesFromValidInputStream() {
        // given
        def isFactory = new ClasspathInputStreamFactory("/test.properties")
        def factory = new InputStreamPropertiesFactory(isFactory)

        // when
        def properties = factory.produce()

        // then
        [
               'property1.x': 'p1v',
               'property1.y': 'p1y',
               'property2.x': 'p2x',
        ].each { k, v ->
            assert properties.get(k) == v
        }
    }

}
