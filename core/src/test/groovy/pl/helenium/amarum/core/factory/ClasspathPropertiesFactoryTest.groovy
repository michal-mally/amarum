package pl.helenium.amarum.core.factory

import org.testng.annotations.Test

class ClasspathPropertiesFactoryTest {

    @Test(expectedExceptions = NullPointerException.class)
    void shallThrowExceptionWhenNonExistingClasspathLocationIsPassed() {
        // given
        def location = "NON_EXISTING"
        def factory = new ClasspathPropertiesFactory(location)

        // when
        factory.produce()

        // then
        // exception expected
    }

    @Test
    void shallReadPropertiesFromExistingClasspathResource() {
        // given
        def location = "/test.properties"
        def factory = new ClasspathPropertiesFactory(location)

        // when
        def properties = factory.produce()

        // then
        [
                "property1.x": "p1v",
                "property1.y": "p1y",
                "property2.x": "p2x",
        ].each { k, v ->
            assert properties.get(k) == v
        }
    }

}
