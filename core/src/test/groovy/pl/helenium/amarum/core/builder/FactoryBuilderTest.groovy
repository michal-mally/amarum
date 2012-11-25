package pl.helenium.amarum.core.builder

import org.testng.annotations.Test

import static pl.helenium.amarum.core.builder.Builders.build

class FactoryBuilderTest {

    @Test
    void shallCreateFactoryFromAlternative() {
        // given
        def product1 = new Object()
        def factory1 = build().factory().fromProduct(product1)

        def product2 = new Object()
        def factory2 = build().factory().fromProduct(product2)
        // when
        def altFactory = build().factory().fromAlternative(factory1, factory2)

        // then
        assert altFactory.produce() == factory1.produce()
    }

}
