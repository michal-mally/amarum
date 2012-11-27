package pl.helenium.amarum.core.factory.store

import org.testng.annotations.Test
import pl.helenium.amarum.api.exception.FactoryException
import pl.helenium.amarum.api.store.KeyValueStore
import pl.helenium.amarum.core.factory.WrappingFactory
import pl.helenium.amarum.core.store.InMemoryKeyValueStore

import java.util.regex.Pattern

import static pl.helenium.amarum.core.builder.Builders.build
import static pl.helenium.amarum.core.store.KeyValueStoreUtils.asMap

class FilterKeyValueStoreFactoryTest {

    @Test(expectedExceptions = FactoryException.class)
    void shallThrowExceptionWhenNullFactory() {
        // given

        // when
        build().keyValueStoreFactory().filter(null, ~/.*/).produce()

        // then
        // exception expected
    }

    @Test(expectedExceptions = FactoryException.class)
    void shallThrowExceptionWhenNoPatterns() {
        // given
        def backingFactory = new WrappingFactory<KeyValueStore>([:] as InMemoryKeyValueStore)

        // when
        build().keyValueStoreFactory().filter(backingFactory).produce()

        // then
        // exception expected
    }

    @Test(expectedExceptions = FactoryException.class)
    void shallThrowExceptionWhenNullPatterns() {
        // given
        def backingFactory = new WrappingFactory<KeyValueStore>([:] as InMemoryKeyValueStore)
        def patterns = [null] as Pattern[]

        // when
        build().keyValueStoreFactory().filter(backingFactory, patterns).produce()

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
        def factory = build().keyValueStoreFactory().filter(backingFactory, pattern)

        // then
        assert asMap(factory.produce()) == [:]
    }

    @Test
    void shallReturnAllKeysIfPatternAcceptsAll() {
        // given
        def backingMap = [a: 'av', b: 'bv', c: 'cv']
        def backingKeyValueStore = backingMap as InMemoryKeyValueStore
        def backingFactory = new WrappingFactory<KeyValueStore>(backingKeyValueStore)
        def pattern = ~/.*/

        // when
        def factory = build().keyValueStoreFactory().filter(backingFactory, pattern)

        // then
        assert asMap(factory.produce()) == backingMap
    }

    @Test
    void shallKeepAllKeysThatMatchesAtLeastOnePattern() {
        // given
        def backingMap = [a: 'av', b: 'bv', c: 'cv']
        def backingKeyValueStore = backingMap as InMemoryKeyValueStore
        def backingFactory = new WrappingFactory<KeyValueStore>(backingKeyValueStore)
        def patterns = [~/a/, ~/b/] as Pattern[]

        // when
        def factory = build().keyValueStoreFactory().filter(backingFactory, patterns)

        // then
        assert asMap(factory.produce()) == (backingMap - [c: 'cv'])
    }

}
