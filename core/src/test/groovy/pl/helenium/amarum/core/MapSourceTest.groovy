package pl.helenium.amarum.core

import org.testng.annotations.Test

class MapSourceTest {

    @Test(expectedExceptions = NullPointerException.class)
    void shallNotAcceptNullAsBackingMap() {
        // given

        // when
        new MapSource(null);

        // then
        // exception expected
    }

    @Test(expectedExceptions = UnsupportedOperationException.class)
    void mapReturnedByGetAllShallNotBeModifiable() {
        // given
        def source = new MapSource()

        // when
        source.getAll().put("key", "value")

        // then
        // exception expected
    }

    @Test
    void shallReturnNullIfKeyIsMissing() {
        // given
        def source = new MapSource([:])

        // when
        def value = source.get("NON_EXISTING_KEY")

        // then
        assert ! value
    }

    @Test
    void shallReturnValueIfEntryIsPresent() {
        // given
        def source = new MapSource([someKey : "someValue"])

        // when
        def value = source.get("someKey")

        // then
        assert value == "someValue"
    }

    @Test
    void shallReturnMapWhenGetAll() {
        // given
        def sourceMap = [someKey: "someValue"]
        def source = new MapSource(sourceMap)

        // when
        def allFromSource = source.all

        // then
        assert allFromSource == sourceMap
    }

}
