package pl.helenium.amarum.core.factory

import org.testng.annotations.Test
import pl.helenium.amarum.api.factory.Factory
import pl.helenium.amarum.api.exception.FactoryException

import static org.mockito.Mockito.mock
import static org.mockito.Mockito.when

class AlternativeFactoryTest {

    @Test(expectedExceptions = IllegalArgumentException.class)
    void shallThrowExceptionIfNoFactoryIsPassed() {
        // given

        // when
        new AlternativeFactory()

        // then
        // exception expected
    }

    @Test(expectedExceptions = NullPointerException.class)
    void shallThrowExceptionIfNullIsPassedInsteadOfListOfFactories() {
        // given

        // when
        new AlternativeFactory(null)

        // then
        // exception expected
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    void shallThrowExceptionIfAnyFactoryPassedIsNull() {
        // given

        // when
        new AlternativeFactory([null] as Factory[])

        // then
        // exception expected
    }

    @Test(expectedExceptions = FactoryException.class)
    void shallThrowFactoryExceptionExceptionIfAllAlternativeFactoriesAreUnableToProduce() {
        // given
        def failingFactory = mock(Factory.class)
        when(failingFactory.produce()).thenThrow(new RuntimeException())

        def factory = new AlternativeFactory(failingFactory, failingFactory, failingFactory)

        // when
        factory.produce();

        // then
        // exception expected
    }

    @Test
    void shallReturnObjectFromFirstNonFailingFactory() {
        // given
        def factory1 = mock(Factory.class)
        when(factory1.produce()).thenThrow(new RuntimeException())

        def validObject1 = new Object()
        def factory2 = new WrappingFactory(validObject1)

        def validObject2 = new Object()
        def factory3 = new WrappingFactory(validObject2)

        def factory = new AlternativeFactory(factory1, factory2, factory3)

        // when
        def object = factory.produce();

        // then
        assert object.is(validObject1)
    }

}
