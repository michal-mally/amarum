package pl.helenium.amarum.core.factory.store

import org.testng.annotations.Test
import pl.helenium.amarum.api.exception.FactoryException
import pl.helenium.amarum.api.factory.Factory
import pl.helenium.amarum.core.factory.WrappingFactory

import static org.mockito.Mockito.mock
import static org.mockito.Mockito.when
import static pl.helenium.amarum.core.builder.Builders.build
import static pl.helenium.amarum.core.store.KeyValueStoreUtils.asMap

class PropertiesKeyValueStoreFactoryTest {

    @Test(expectedExceptions = FactoryException.class)
    void shallThrowExceptionWhenNullFactoryIsPassed() {
        // given
        def propertiesFactory = null

        // when
        build().keyValueStoreFactory().fromPropertiesFactory(null).produce()

        // then
        // exception expected
    }

    @Test(expectedExceptions = FactoryException.class)
    void shallThrowFactoryExceptionWhenPropertiesFactoryThrowsAnyException() {
        // given
        def propertiesFactory = mock(Factory.class)
        when(propertiesFactory.produce()).thenThrow(new RuntimeException())

        def factory = build().keyValueStoreFactory().fromPropertiesFactory(propertiesFactory);

        // when
        factory.produce()

        // then
        // exception expected
    }

    @Test
    void shallReturnKeyValueStoreContainingAllEntriesFromProperties() {
        // given
        def map = [a: 'av', b: 'bv']
        def properties = new Properties()
        map.each { k, v -> properties.setProperty(k, v) }

        // when
        def factory = build().keyValueStoreFactory().fromPropertiesFactory(properties as WrappingFactory)

        // then
        assert asMap(factory.produce()) == map
    }

}
