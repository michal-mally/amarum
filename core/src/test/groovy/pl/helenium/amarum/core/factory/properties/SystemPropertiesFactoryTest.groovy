package pl.helenium.amarum.core.factory.properties

import org.testng.annotations.Test

class SystemPropertiesFactoryTest {

    @Test
    void shallProduceSamePropertiesAsSystemGetProperties() {
        // given

        // when
        def propertiesFromFactory = new SystemPropertiesFactory().produce()

        // then
        assert propertiesFromFactory == System.properties
    }

}
