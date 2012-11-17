package pl.helenium.amarum.core

import org.testng.annotations.Test

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
    void shallReturnNullIfKeyIsMissing() {
        // given
        def source = new InMemorySource([:])

        // when
        def value = source.get("NON_EXISTING_KEY")

        // then
        assert ! value
    }

    @Test
    void shallReturnValueIfEntryIsPresent() {
        // given
        def source = new InMemorySource([someKey : "someValue"])

        // when
        def value = source.get("someKey")

        // then
        assert value == "someValue"
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
