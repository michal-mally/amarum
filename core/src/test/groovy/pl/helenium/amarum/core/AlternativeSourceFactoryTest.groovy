package pl.helenium.amarum.core

import org.testng.annotations.Test
import pl.helenium.amarum.api.Source
import pl.helenium.amarum.api.SourceCreationException
import pl.helenium.amarum.api.SourceFactory

import static org.mockito.Mockito.mock
import static org.mockito.Mockito.when

class AlternativeSourceFactoryTest {

    @Test(expectedExceptions = IllegalArgumentException.class)
    void shallThrowExceptionIfNoSourceFactoryIsPassed() {
        // given

        // when
        new AlternativeSourceFactory()

        // then
        // exception expected
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    void shallThrowExceptionIfAnySourceFactoryPassedIsNull() {
        // given

        // when
        new AlternativeSourceFactory([null] as SourceFactory[])

        // then
        // exception expected
    }

    @Test(expectedExceptions = SourceCreationException.class)
    void shallThrowSourceCreationExceptionIfAllAlternativeSourceFactoriesAreUnableToCreateSource() {
        // given
        def failingFactory = mock(SourceFactory.class)
        when(failingFactory.createSource()).thenThrow(new RuntimeException())

        def factory = new AlternativeSourceFactory(failingFactory, failingFactory, failingFactory)

        // when
        factory.createSource();

        // then
        // exception expected
    }

    @Test
    void shallReturnSourceFromFirstNonFailingFactory() {
        // given
        def factory1 = mock(SourceFactory.class)
        when(factory1.createSource()).thenThrow(new RuntimeException())

        def validSource1 = mock(Source.class)
        def factory2 = new WrappingSourceFactory(validSource1)

        def validSource2 = mock(Source.class)
        def factory3 = new WrappingSourceFactory(validSource2)

        def factory = new AlternativeSourceFactory(factory1, factory2, factory3)

        // when
        def source = factory.createSource();

        // then
        assert source.is(validSource1)
    }

}
