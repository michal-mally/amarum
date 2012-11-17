package pl.helenium.amarum.core.source.factory

import org.testng.annotations.Test
import pl.helenium.amarum.api.Factory
import pl.helenium.amarum.api.FactoryException
import pl.helenium.amarum.core.factory.WrappingFactory

import static org.mockito.Mockito.mock
import static org.mockito.Mockito.when

class PropertiesSourceFactoryTest {

    @Test(expectedExceptions = NullPointerException.class)
    void shallThrowExceptionWhenNullFactoryIsPassed() {
        // given
        def propertiesFactory = null

        // when
        new PropertiesSourceFactory(propertiesFactory)

        // then
        // exception expected
    }

    @Test(expectedExceptions = FactoryException.class)
    void shallThrowFactoryExceptionWhenPropertiesFactoryThrowsAnyException() {
        // given
        def propertiesFactory = mock(Factory.class)
        when(propertiesFactory.produce()).thenThrow(new RuntimeException())

        def factory = new PropertiesSourceFactory(propertiesFactory);

        // when
        factory.produce()

        // then
        // exception expected
    }

    @Test
    void shallReturnSourceContainingAllEntriesFromProperties() {
        // given
        def map = [a: 'av', b: 'bv']
        def properties = new Properties()
        map.each { k, v -> properties.setProperty(k, v) }

        // when
        def factory = new PropertiesSourceFactory(properties as WrappingFactory)

        // then
        assert factory.produce().all == map
    }

}
