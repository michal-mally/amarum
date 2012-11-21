package pl.helenium.amarum.core.source.factory

import org.testng.annotations.Test
import pl.helenium.amarum.api.Factory
import pl.helenium.amarum.api.Source
import pl.helenium.amarum.api.exception.RefreshException
import pl.helenium.amarum.core.factory.WrappingFactory
import pl.helenium.amarum.core.source.InMemorySource

import static org.mockito.Mockito.mock
import static org.mockito.Mockito.when

class RefreshableSourceTest {

    @Test(expectedExceptions = NullPointerException.class)
    void shallThrowExceptionIfFactoryIsNull() {
        // given
        def factory = null

        // when
        new RefreshableSource(factory)

        // then
        // exception expected
    }

    @Test(expectedExceptions = RefreshException.class)
    void shallThrowRefreshExceptionIfFactoryProducesNullSource() {
        // given
        def factory = mock(Factory.class)
        when(factory.produce()).thenReturn(null)

        // when
        new RefreshableSource(factory)

        // then
        // exception expected
    }

    @Test(expectedExceptions = RefreshException.class)
    void shallThrowRefreshExceptionIfOnRefreshFactoryThrowsException() {
        // given
        def factory = mock(Factory.class)
        when(factory.produce()).thenReturn([:] as InMemorySource)
        def refreshable = new RefreshableSource(factory)

        // when
        when(factory.produce()).thenThrow(new RuntimeException())
        refreshable.refresh()

        // then
        // exception expected
    }

    @Test
    void shallReturnSameEntriesAsUnderlyingSourceFactory() {
        // given
        def backingMap = [a: 'av']
        def factory = new WrappingFactory<Source>(backingMap as InMemorySource)

        // when
        def refreshable = new RefreshableSource(factory)

        // then
        assert refreshable.all == backingMap
    }

    @Test
    void shallNotReflectChangesToSourceEvenIfNotRefreshed() {
        // given
        def backingMap = [a: 'av']
        def factory = new WrappingFactory<Source>(backingMap as InMemorySource)
        def refreshable = new RefreshableSource(factory)

        // when
        backingMap.b = 'bv'

        // then
        assert refreshable.all == backingMap
    }

    @Test
    void shallNotReflectChangesToFactoryIfNotRefreshed() {
        // given
        def backingMap1 = [a: 'av']
        def factory = mock(Factory.class)
        when(factory.produce()).thenReturn(backingMap1 as InMemorySource)

        def refreshable = new RefreshableSource(factory)

        // when
        def backingMap2 = [b: 'bv']
        when(factory.produce()).thenReturn(backingMap2 as InMemorySource)

        // then
        assert refreshable.all == backingMap1
    }

    @Test
    void shallReflectChangesToFactoryIfRefreshed() {
        // given
        def backingMap1 = [a: 'av']
        def factory = mock(Factory.class)
        when(factory.produce()).thenReturn(backingMap1 as InMemorySource)

        def refreshable = new RefreshableSource(factory)

        // when
        def backingMap2 = [b: 'bv']
        when(factory.produce()).thenReturn(backingMap2 as InMemorySource)
        refreshable.refresh()

        // then
        assert refreshable.all == backingMap2
    }

}
