package pl.helenium.amarum.core.factory.store

import org.testng.annotations.Test
import pl.helenium.amarum.api.store.KeyValueStore
import pl.helenium.amarum.core.factory.WrappingFactory
import pl.helenium.amarum.core.store.InMemoryKeyValueStore

import java.util.regex.Pattern

import static pl.helenium.amarum.core.store.KeyValueStoreUtils.asMap

class FilterKeyValueStoreFactoryTest {

    @Test(expectedExceptions = NullPointerException.class)
    void shallThrowExceptionWhenNullFactory() {
        // given

        // when
        new FilterKeyValueStoreFactory(null, ~/.*/)

        // then
        // exception expected
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    void shallThrowExceptionWhenNoPatterns() {
        // given
        def backingFactory = new WrappingFactory<KeyValueStore>([:] as InMemoryKeyValueStore)

        // when
        new FilterKeyValueStoreFactory(backingFactory)

        // then
        // exception expected
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    void shallThrowExceptionWhenNullPatterns() {
        // given
        def backingFactory = new WrappingFactory<KeyValueStore>([:] as InMemoryKeyValueStore)
        def patterns = [null] as Pattern[]

        // when
        new FilterKeyValueStoreFactory(backingFactory, patterns)

        // then
        // exception expected
    }

    @Test
    void shallReturnEmptyKeyValueStoreOnEmptyPattern() {
        // given
        def backingKeyValueStore = [a:'av', b: 'bv'] as InMemoryKeyValueStore
        def backingFactory = new WrappingFactory<KeyValueStore>(backingKeyValueStore)
        def pattern = ~""

        // when
        def factory = new FilterKeyValueStoreFactory(backingFactory, pattern)

        // then
        assert asMap(factory.produce()) == [:]
    }

    @Test
    void shallReturnAllKeysIfPatternAcceptsAll() {
        // given
        def backingMap = [a: 'av', b: 'bv', c: 'cv'] as TreeMap
        def backingKeyValueStore = backingMap as InMemoryKeyValueStore
        def backingFactory = new WrappingFactory<KeyValueStore>(backingKeyValueStore)
        def pattern = ~/.*/

        // when
        def factory = new FilterKeyValueStoreFactory(backingFactory, pattern)

        // then
        assert asMap(factory.produce()) == backingMap
    }

    @Test
    void shallKeepAllKeysThatMatchesAtLeastOnePattern() {
        // given
        def backingMap = [a: 'av', b: 'bv', c: 'cv'] as TreeMap
        def backingKeyValueStore = backingMap as InMemoryKeyValueStore
        def backingFactory = new WrappingFactory<KeyValueStore>(backingKeyValueStore)
        def patterns = [~/a/, ~/b/] as Pattern[]

        // when
        def factory = new FilterKeyValueStoreFactory(backingFactory, patterns)

        // then
        assert asMap(factory.produce()) == (backingMap - [c: 'cv'])
    }

}
