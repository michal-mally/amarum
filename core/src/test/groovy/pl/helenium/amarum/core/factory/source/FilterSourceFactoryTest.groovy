package pl.helenium.amarum.core.factory.source

import org.testng.annotations.Test
import pl.helenium.amarum.api.Source
import pl.helenium.amarum.core.factory.WrappingFactory
import pl.helenium.amarum.core.source.InMemorySource

import java.util.regex.Pattern

class FilterSourceFactoryTest {

    @Test(expectedExceptions = NullPointerException.class)
    void shallThrowExceptionWhenNullFactory() {
        // given

        // when
        new FilterSourceFactory(null, ~/.*/)

        // then
        // exception expected
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    void shallThrowExceptionWhenNoPatterns() {
        // given
        def backingFactory = new WrappingFactory<Source>([:] as InMemorySource)

        // when
        new FilterSourceFactory(backingFactory)

        // then
        // exception expected
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    void shallThrowExceptionWhenNullPatterns() {
        // given
        def backingFactory = new WrappingFactory<Source>([:] as InMemorySource)
        def patterns = [null] as Pattern[]

        // when
        new FilterSourceFactory(backingFactory, patterns)

        // then
        // exception expected
    }

    @Test
    void shallReturnEmptySourceOnEmptyPattern() {
        // given
        def backingSource = [a:'av', b: 'bv'] as InMemorySource
        def backingFactory = new WrappingFactory<Source>(backingSource)
        def pattern = ~""

        // when
        def factory = new FilterSourceFactory(backingFactory, pattern)

        // then
        assert factory.produce().all == [:]
    }

    @Test
    void shallReturnAllKeysIfPatternAcceptsAll() {
        // given
        def backingMap = [a: 'av', b: 'bv', c: 'cv']
        def backingSource = backingMap as InMemorySource
        def backingFactory = new WrappingFactory<Source>(backingSource)
        def pattern = ~/.*/

        // when
        def factory = new FilterSourceFactory(backingFactory, pattern)

        // then
        assert factory.produce().all == backingMap
    }

    @Test
    void shallKeepAllKeysThatMatchesAtLeastOnePattern() {
        // given
        def backingMap = [a: 'av', b: 'bv', c: 'cv']
        def backingSource = backingMap as InMemorySource
        def backingFactory = new WrappingFactory<Source>(backingSource)
        def patterns = [~/a/, ~/b/] as Pattern[]

        // when
        def factory = new FilterSourceFactory(backingFactory, patterns)

        // then
        assert factory.produce().all == (backingMap - [c: 'cv'])
    }

}
