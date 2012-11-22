package pl.helenium.amarum.core.factory

import org.testng.annotations.Test

class WrappingFactoryTest {

    @Test
    void shallAllowNullProduct() {
        // given
        def product = null

        // when
        def factory = new WrappingFactory<Object>(product)

        // then
        assert ! factory.produce()
    }

}
