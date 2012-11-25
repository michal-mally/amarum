package pl.helenium.amarum.core.builder

import org.testng.annotations.Test

import static pl.helenium.amarum.core.builder.Builders.build
import static pl.helenium.amarum.core.store.KeyValueStoreUtils.asMap

class KeyValueStoreBuilderTest {

    @Test
    void shallCreateKeyValueStoreFromMap() {
        // given
        def sourceMap = [key1: 'value1', key2: 'value2']

        // when
        def keyValueStore = build().keyValueStore().fromMap(sourceMap)

        // then
        assert asMap(keyValueStore) == sourceMap
    }

}
