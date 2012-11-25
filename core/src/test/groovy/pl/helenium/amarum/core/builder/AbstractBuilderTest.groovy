package pl.helenium.amarum.core.builder

import org.testng.annotations.Test
import pl.helenium.amarum.api.exception.BuildException

class AbstractBuilderTest {

    private static final String EXISTING_PROPERTY = "existingProperty"

    @Test(expectedExceptions = BuildException.class)
    void shallThrowExceptionWhenWithNonExistingProperty() {
        // given
        def builder = new TestBuilder()

        // when
        builder.with("nonExistingProperty", "x").build()

        // then
        // exception expected
    }

    @Test
    void shallNotThrowExceptionWhenWithNonExistingPropertyWhenIgnoreNonExistingProperties() {
        // given
        def builder = new TestBuilder()

        // when
        def product = builder.ignoreNonExistingProperties().with("nonExistingProperty", "x").build()

        // then
        assert product
    }

    @Test
    void shallSetExistingProperty() {
        // given
        def builder = new TestBuilder()

        // when
        def product = builder.with(EXISTING_PROPERTY, "x").build()

        // then
        assert product["existingProperty"] == "x"
    }

    class TestBuilder extends AbstractBuilder<TestBuilder> {

        Object build() {
            return configure(new Object() {
                def existingProperty = ""
            })
        }

        @Override
        protected TestBuilder getThis() {
            this
        }

    }

}
