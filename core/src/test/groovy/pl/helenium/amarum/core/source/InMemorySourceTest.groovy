package pl.helenium.amarum.core.source

import org.testng.annotations.Test
import pl.helenium.amarum.core.source.InMemorySource

class InMemorySourceTest {

    @Test(expectedExceptions = NullPointerException.class)
    void shallNotAcceptNullAsBackingMap() {
        // given

        // when
        new InMemorySource(null);

        // then
        // exception expected
    }

    @Test(expectedExceptions = UnsupportedOperationException.class)
    void mapReturnedByGetAllShallNotBeModifiable() {
        // given
        def source = new InMemorySource()

        // when
        source.getAll().put("key", "value")

        // then
        // exception expected
    }

    @Test
    void shallReturnMapWhenGetAll() {
        // given
        def sourceMap = [someKey: "someValue"]
        def source = new InMemorySource(sourceMap)

        // when
        def allFromSource = source.all

        // then
        assert allFromSource == sourceMap
    }

}
