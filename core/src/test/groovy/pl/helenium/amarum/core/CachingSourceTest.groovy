package pl.helenium.amarum.core

import org.testng.annotations.Test
import org.mockito.Mockito
import pl.helenium.amarum.api.Source

import static org.mockito.Mockito.mock
import static org.mockito.Mockito.verifyZeroInteractions
import static org.mockito.Mockito.verify
import static org.mockito.Mockito.times
import static org.mockito.Matchers.anyString

class CachingSourceTest {

    private static final String SOME_KEY = "someKey"

    @Test(expectedExceptions = NullPointerException.class)
    void shallThrowExceptionWhenNullSourcePassed() {
        // given
        def backingSource = null

        // when
        def source = new CachingSource(backingSource)

        // then
        // exception expected
    }

    @Test
    void shallReturnTheSameValuesAsBackingSourceWhenGetCall() {
        // given
        def backingSource = [(SOME_KEY): "someValue"] as InMemorySource

        // when
        def source = new CachingSource(backingSource)

        // then
        assert source.get(SOME_KEY) == backingSource.get(SOME_KEY)
    }

    @Test
    void shallReturnTheSameValuesAsBackingSourceWhenGetAllCall() {
        // given
        def backingSource = [(SOME_KEY): "someValue"] as InMemorySource

        // when
        def source = new CachingSource(backingSource)

        // then
        assert source.all == backingSource.all
    }

    @Test
    void shallNotInteractWithBackingSourceBeforeFirstCall() {
        // given
        def backingSource = mock(Source.class)

        // when
        def source = new CachingSource(backingSource)

        // then
        verifyZeroInteractions(backingSource)
    }

    @Test
    void shallNotInteractWithBackingSourceMoreThanOnce() {
        // given
        def backingSource = mock(Source.class)
        def source = new CachingSource(backingSource)

        // when
        source.get()
        source.getAll()

        // then
        verify(backingSource, times(0)).get(anyString())
        verify(backingSource, times(1)).getAll()
    }


}
