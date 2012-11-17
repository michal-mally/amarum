package pl.helenium.amarum.core.factory

import org.testng.annotations.Test
import pl.helenium.amarum.api.Factory
import pl.helenium.amarum.api.FactoryException
import pl.helenium.amarum.api.Source

import static org.mockito.Mockito.mock
import static org.mockito.Mockito.when

class AlternativeFactoryTest {

    @Test(expectedExceptions = IllegalArgumentException.class)
    void shallThrowExceptionIfNoSourceFactoryIsPassed() {
        // given

        // when
        new AlternativeFactory()

        // then
        // exception expected
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    void shallThrowExceptionIfAnySourceFactoryPassedIsNull() {
        // given

        // when
        new AlternativeFactory([null] as Factory[])

        // then
        // exception expected
    }

    @Test(expectedExceptions = FactoryException.class)
    void shallThrowSourceCreationExceptionIfAllAlternativeSourceFactoriesAreUnableToCreateSource() {
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
    void shallReturnSourceFromFirstNonFailingFactory() {
        // given
        def factory1 = mock(Factory.class)
        when(factory1.produce()).thenThrow(new RuntimeException())

        def validSource1 = mock(Source.class)
        def factory2 = new WrappingFactory(validSource1)

        def validSource2 = mock(Source.class)
        def factory3 = new WrappingFactory(validSource2)

        def factory = new AlternativeFactory(factory1, factory2, factory3)

        // when
        def source = factory.produce();

        // then
        assert source.is(validSource1)
    }

}
