package pl.helenium.amarum.core.factory.store

import org.testng.annotations.Test
import pl.helenium.amarum.api.exception.FactoryException
import pl.helenium.amarum.api.factory.Factory
import pl.helenium.amarum.api.store.KeyValueStore
import pl.helenium.amarum.core.factory.WrappingFactory
import pl.helenium.amarum.core.store.InMemoryKeyValueStore

import static org.mockito.Mockito.mock
import static org.mockito.Mockito.when
import static pl.helenium.amarum.core.store.KeyValueStoreUtils.asMap

class MergedKeyValueStoreFactoryTest {

    @Test
    void shallReturnEmptyKeyValueStoreWhenNoFactoryIsPassed() {
        // given

        // when
        def factory = new MergedKeyValueStoreFactory()

        // then
        assert asMap(factory.produce()) == [:]
    }

    @Test(expectedExceptions = NullPointerException.class)
    void shallThrowFactoryExceptionWhenNullPassedInsteadOfListOfFactories() {
        // given

        // when
        new MergedKeyValueStoreFactory(null)

        // then
        // exception expected
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    void shallThrowFactoryExceptionWhenAnyOfFactoriesIsNull() {
        // given

        // when
        new MergedKeyValueStoreFactory([null] as Factory[])

        // then
        // exception expected
    }

    @Test(expectedExceptions = FactoryException.class)
    void shallThrowFactoryExceptionIfAnyOfFactoriesThrowsException() {
        // given
        def okFactory = new WrappingFactory<KeyValueStore>([:] as InMemoryKeyValueStore)

        def failingFactory = mock(Factory.class)
        when(failingFactory.produce()).thenThrow(new RuntimeException())

        def factory = new MergedKeyValueStoreFactory(okFactory, failingFactory, okFactory)

        // when
        factory.produce()

        // then
        // exception expected
    }

    @Test
    void shallReturnEqualKeyValueStoreIfJustOneFactoryIsPassed() {
        // given
        def backingFactory = new WrappingFactory<KeyValueStore>([a: 'b'] as InMemoryKeyValueStore)

        // when
        def factory = new MergedKeyValueStoreFactory(backingFactory)

        // then
        assert asMap(factory.produce()) == asMap(backingFactory.produce())
    }

    @Test
    void shallContainEntriesFromAllFactories() {
        // given
        def backingFactories = (1..3).collect {
            def backingMap = [("key-$it" as String): "value-$it" as String]
            new WrappingFactory<KeyValueStore>(backingMap as InMemoryKeyValueStore)
        }
        def factory = new MergedKeyValueStoreFactory(backingFactories as Factory[])

        // when
        def keyValueStore = factory.produce()

        // then
        (1..3).each {
            assert asMap(keyValueStore)["key-$it" as String] == "value-$it"
        }
    }

    @Test
    void shallValueFromLastFactoryWinWhenConflictingKeys() {
        // given
        def backingFactories = (1..3).collect {
            def backingMap = [key: "value-$it" as String]
            new WrappingFactory<KeyValueStore>(backingMap as InMemoryKeyValueStore)
        }
        def factory = new MergedKeyValueStoreFactory(backingFactories as Factory[])

        // when
        def keyValueStore = factory.produce()

        // then
        assert keyValueStore.getValue('key') == "value-3"
    }

}
