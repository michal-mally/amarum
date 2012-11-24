package pl.helenium.amarum.core.store

import org.testng.annotations.Test

import static pl.helenium.amarum.core.store.KeyValueStoreUtils.asMap

class InMemoryKeyValueStoreTest {

    @Test(expectedExceptions = NullPointerException.class)
    void shallNotAcceptNullAsBackingMap() {
        // given

        // when
        new InMemoryKeyValueStore(null);

        // then
        // exception expected
    }

    @Test(expectedExceptions = UnsupportedOperationException.class)
    void setReturnedByGetAllKeysShallNotBeModifiable() {
        // given
        def keyValueStore = new InMemoryKeyValueStore([:])

        // when
        keyValueStore.allKeys.add "key"

        // then
        // exception expected
    }

    @Test
    void shallReturnMapWhenGetAll() {
        // given
        def keyValueStoreMap = [someKey: "someValue"]
        def keyValueStore = new InMemoryKeyValueStore(keyValueStoreMap)

        // when
        def allFromKeyValueStore = asMap(keyValueStore)

        // then
        assert allFromKeyValueStore == keyValueStoreMap
    }

}
