package pl.helenium.amarum.core.factory.store

import org.testng.annotations.Test
import pl.helenium.amarum.api.exception.FactoryException
import pl.helenium.amarum.api.store.KeyValueStore
import pl.helenium.amarum.core.store.InMemoryKeyValueStore

import static org.mockito.Mockito.*
import static pl.helenium.amarum.core.store.KeyValueStoreUtils.asMap

class CachedKeyValueStoreFactoryTest {

    private static final String SOME_KEY = "someKey"

    @Test(expectedExceptions = NullPointerException.class)
    void shallThrowExceptionWhenNullKeyValueStorePassed() {
        // given
        def backingKeyValueStore = null

        // when
        def factory = new CachedKeyValueStoreFactory(backingKeyValueStore)

        // then
        // exception expected
    }

    @Test(expectedExceptions = FactoryException.class)
    void shallThrowKeyValueStoreCreationExceptionWhenBackingKeyValueStoreThrowsAnyException() {
        // given
        def backingKeyValueStore = mock(KeyValueStore.class)
        when(backingKeyValueStore.allKeys).thenThrow(new RuntimeException())

        def factory = new CachedKeyValueStoreFactory(backingKeyValueStore)

        // when
        factory.produce()

        // then
        // exception expected
    }

    @Test
    void shallReturnTheSameValuesAsBackingKeyValueStoreWhenGetAllCall() {
        // given
        def backingKeyValueStore = [(SOME_KEY): "someValue"] as InMemoryKeyValueStore
        def factory = new CachedKeyValueStoreFactory(backingKeyValueStore)

        // when
        def keyValueStore = factory.produce()

        // then
        assert asMap(keyValueStore) == asMap(backingKeyValueStore)
    }

    @Test
    void shallNotInteractWithBackingKeyValueStoreBeforeFirstCall() {
        // given
        def backingKeyValueStore = mock(KeyValueStore.class)

        // when
        new CachedKeyValueStoreFactory(backingKeyValueStore)

        // then
        verifyZeroInteractions(backingKeyValueStore)
    }

    @Test
    void shallNotInteractWithKeyValueStoreMoreThanOnce() {
        // given
        def backingKeyValueStore = mock(KeyValueStore.class)
        when(backingKeyValueStore.allKeys).thenReturn(["key"] as TreeSet)
        def keyValueStore = new CachedKeyValueStoreFactory(backingKeyValueStore).produce()

        // when
        keyValueStore.allKeys
        keyValueStore.getValue('x')
        keyValueStore.allKeys
        keyValueStore.getValue('y')

        // then
        verify(backingKeyValueStore, times(1)).allKeys
    }

}
