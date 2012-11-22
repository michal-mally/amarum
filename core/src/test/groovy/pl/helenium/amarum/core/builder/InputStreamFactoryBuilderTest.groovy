package pl.helenium.amarum.core.builder

import org.testng.annotations.Test
import pl.helenium.amarum.api.exception.BuildException

import static pl.helenium.amarum.core.builder.Builders.inputStreamFactory

class InputStreamFactoryBuilderTest {

    @Test(expectedExceptions = BuildException.class)
    void shallThrowBuildExceptionWhenSettingNonExistingPropertyWithIgnoreFlagSetToFalse() {
        // given
        inputStreamFactory()
                .with("non-existing", "value")

        // when
                .fromClasspath("test.properties")

        // then
        // exception expected
    }

    @Test
    void shallIgnoreNonExistingPropertyWithIgnoreFlagSetToTrue() {
        // given
        def factory = inputStreamFactory()
                .ignoreNonExistingProperties()
                .with("non-existing", "value")

        // when
                .fromClasspath("/test.properties")

        // then
        assert factory.produce()
    }

}
