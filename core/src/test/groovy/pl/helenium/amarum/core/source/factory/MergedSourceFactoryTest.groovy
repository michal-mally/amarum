package pl.helenium.amarum.core.source.factory

import org.testng.annotations.Test
import pl.helenium.amarum.api.Factory
import pl.helenium.amarum.api.Source
import pl.helenium.amarum.api.exception.FactoryException
import pl.helenium.amarum.core.factory.WrappingFactory
import pl.helenium.amarum.core.source.InMemorySource

import static org.mockito.Mockito.mock
import static org.mockito.Mockito.when

class MergedSourceFactoryTest {

    @Test
    void shallReturnEmptySourceWhenNoFactoryIsPassed() {
        // given

        // when
        def factory = new MergedSourceFactory()

        // then
        assert factory.produce().all == [:]
    }

    @Test(expectedExceptions = NullPointerException.class)
    void shallThrowFactoryExceptionWhenNullPassedInsteadOfListOfFactories() {
        // given

        // when
        new MergedSourceFactory(null)

        // then
        // exception expected
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    void shallThrowFactoryExceptionWhenAnyOfFactoriesIsNull() {
        // given

        // when
        new MergedSourceFactory([null] as Factory[])

        // then
        // exception expected
    }

    @Test(expectedExceptions = FactoryException.class)
    void shallThrowFactoryExceptionIfAnyOfFactoriesThrowsException() {
        // given
        def okFactory = new WrappingFactory<Source>([:] as InMemorySource)

        def failingFactory = mock(Factory.class)
        when(failingFactory.produce()).thenThrow(new RuntimeException())

        def factory = new MergedSourceFactory(okFactory, failingFactory, okFactory)

        // when
        factory.produce()

        // then
        // exception expected
    }

    @Test
    void shallReturnEqualSourceIfJustOneFactoryIsPassed() {
        // given
        def backingFactory = new WrappingFactory<Source>([a: 'b'] as InMemorySource)

        // when
        def factory = new MergedSourceFactory(backingFactory)

        // then
        assert factory.produce().all == backingFactory.produce().all
    }

    @Test
    void shallContainEntriesFromAllFactories() {
        // given
        def backingFactories = (1..3).collect {
            new WrappingFactory<Source>([("key-$it" as String): "value-$it"] as InMemorySource)
        }
        def factory = new MergedSourceFactory(backingFactories as Factory[])

        // when
        def source = factory.produce()

        // then
        (1..3).each {
            assert source.all["key-$it"] == "value-$it"
        }
    }

    @Test
    void shallValueFromLastFactoryWinWhenConflictingKeys() {
        // given
        def backingFactories = (1..3).collect {
            new WrappingFactory<Source>([key: "value-$it"] as InMemorySource)
        }
        def factory = new MergedSourceFactory(backingFactories as Factory[])

        // when
        def source = factory.produce()

        // then
        assert source.all.key == "value-3"
    }

}
