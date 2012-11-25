package pl.helenium.amarum.core.factory.store

import org.testng.annotations.Test
import pl.helenium.amarum.api.exception.RefreshException
import pl.helenium.amarum.api.factory.Factory
import pl.helenium.amarum.api.store.KeyValueStore
import pl.helenium.amarum.core.factory.WrappingFactory
import pl.helenium.amarum.core.store.InMemoryKeyValueStore

import static org.mockito.Mockito.mock
import static org.mockito.Mockito.when
import static pl.helenium.amarum.core.store.KeyValueStoreUtils.asMap

class DefaultRefreshableKeyValueStoreTest {

    @Test(expectedExceptions = NullPointerException.class)
    void shallThrowExceptionIfFactoryIsNull() {
        // given
        def factory = null

        // when
        new DefaultRefreshableKeyValueStore(factory)

        // then
        // exception expected
    }

    @Test(expectedExceptions = RefreshException.class)
    void shallThrowRefreshExceptionIfFactoryProducesNullKeyValueStore() {
        // given
        def factory = mock(Factory.class)
        when(factory.produce()).thenReturn(null)

        // when
        new DefaultRefreshableKeyValueStore(factory)

        // then
        // exception expected
    }

    @Test(expectedExceptions = RefreshException.class)
    void shallThrowRefreshExceptionIfOnRefreshFactoryThrowsException() {
        // given
        def factory = mock(Factory.class)
        when(factory.produce()).thenReturn([:] as InMemoryKeyValueStore)
        def refreshable = new DefaultRefreshableKeyValueStore(factory)

        // when
        when(factory.produce()).thenThrow(new RuntimeException())
        refreshable.refresh()

        // then
        // exception expected
    }

    @Test
    void shallReturnSameEntriesAsUnderlyingKeyValueStoreFactory() {
        // given
        def backingMap = [a: 'av']
        def factory = new WrappingFactory<KeyValueStore>(backingMap as InMemoryKeyValueStore)

        // when
        def refreshable = new DefaultRefreshableKeyValueStore(factory)

        // then
        assert asMap(refreshable) == backingMap
    }

    @Test
    void shallReflectChangesToKeyValueStoreIfNotRefreshed() {
        // given
        def backingMap = [a: 'av']
        def factory = new WrappingFactory<KeyValueStore>(backingMap as InMemoryKeyValueStore)
        def refreshable = new DefaultRefreshableKeyValueStore(factory)

        // when
        backingMap.b = 'bv'

        // then
        assert asMap(refreshable) == backingMap
    }

    @Test
    void shallNotReflectChangesToFactoryIfNotRefreshed() {
        // given
        def backingMap1 = [a: 'av']
        def factory = mock(Factory.class)
        when(factory.produce()).thenReturn(backingMap1 as InMemoryKeyValueStore)

        def refreshable = new DefaultRefreshableKeyValueStore(factory)

        // when
        def backingMap2 = [b: 'bv']
        when(factory.produce()).thenReturn(backingMap2 as InMemoryKeyValueStore)

        // then
        assert asMap(refreshable) == backingMap1
    }

    @Test
    void shallReflectChangesToFactoryIfRefreshed() {
        // given
        def backingMap1 = [a: 'av']
        def factory = mock(Factory.class)
        when(factory.produce()).thenReturn(backingMap1 as InMemoryKeyValueStore)

        def refreshable = new DefaultRefreshableKeyValueStore(factory)

        // when
        def backingMap2 = [b: 'bv']
        when(factory.produce()).thenReturn(backingMap2 as InMemoryKeyValueStore)
        refreshable.refresh()

        // then
        assert asMap(refreshable) == backingMap2
    }

}
