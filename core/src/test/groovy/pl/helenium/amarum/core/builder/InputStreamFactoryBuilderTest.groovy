package pl.helenium.amarum.core.builder

import org.apache.commons.lang3.RandomStringUtils
import org.testng.annotations.Test

import static pl.helenium.amarum.core.builder.Builders.build

class InputStreamFactoryBuilderTest {

    private static final def CLASSPATH_RESOURCE = "/test.properties"

    private static final def RESOURCE_CONTENT = InputStreamFactoryBuilderTest.class.getResourceAsStream(CLASSPATH_RESOURCE).text

    @Test
    void shallProduceInputStreamConnectedToExistingResource() {
        // given
        def factory = build().inputStreamFactory()

        // when
                .fromClasspath(CLASSPATH_RESOURCE)

        // then
        assert factory.produce().text == RESOURCE_CONTENT
    }

    @Test
    void shallProduceInputStreamConnectedToExistingFile() {
        // given
        def file = File.createTempFile(RandomStringUtils.randomAlphabetic(10), ".properties")
        file.text = RESOURCE_CONTENT

        // when
        def factory = build().inputStreamFactory().fromFile(file.absolutePath)

        // then
        assert factory.produce().text == RESOURCE_CONTENT
    }

}
