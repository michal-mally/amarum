package pl.helenium.amarum.core.source.factory

import org.testng.annotations.Test
import pl.helenium.amarum.api.FactoryException
import pl.helenium.amarum.api.Source
import pl.helenium.amarum.core.source.InMemorySource

import static org.mockito.Mockito.*

class CachedSourceFactoryTest {

    private static final String SOME_KEY = "someKey"

    @Test(expectedExceptions = NullPointerException.class)
    void shallThrowExceptionWhenNullSourcePassed() {
        // given
        def backingSource = null

        // when
        def factory = new CachedSourceFactory(backingSource)

        // then
        // exception expected
    }

    @Test(expectedExceptions = FactoryException.class)
    void shallThrowSourceCreationExceptionWhenBackingSourceThrowsAnyException() {
        // given
        def backingSource = mock(Source.class)
        when(backingSource.all).thenThrow(new RuntimeException())

        def factory = new CachedSourceFactory(backingSource)

        // when
        factory.produce()

        // then
        // exception expected
    }

    @Test
    void shallReturnTheSameValuesAsBackingSourceWhenGetAllCall() {
        // given
        def backingSource = [(SOME_KEY): "someValue"] as InMemorySource
        def factory = new CachedSourceFactory(backingSource)

        // when
        def source = factory.produce()

        // then
        assert source.all == backingSource.all
    }

    @Test
    void shallNotInteractWithBackingSourceBeforeFirstCall() {
        // given
        def backingSource = mock(Source.class)

        // when
        new CachedSourceFactory(backingSource)

        // then
        verifyZeroInteractions(backingSource)
    }

    @Test
    void shallNotInteractWithBackingSourceMoreThanOnce() {
        // given
        def backingSource = mock(Source.class)
        def source = new CachedSourceFactory(backingSource).produce()

        // when
        source.all
        source.all

        // then
        verify(backingSource, times(1)).all
    }

}
