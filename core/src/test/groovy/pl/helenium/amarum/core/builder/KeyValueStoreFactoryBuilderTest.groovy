package pl.helenium.amarum.core.builder

import org.testng.annotations.Test
import pl.helenium.amarum.core.store.InMemoryKeyValueStore

import static pl.helenium.amarum.core.builder.Builders.build
import static pl.helenium.amarum.core.store.KeyValueStoreUtils.asMap

class KeyValueStoreFactoryBuilderTest {

    @Test
    void shallCreateKeyValueStoreFromPropertiesFactory() {
        // given
        def properties = new Properties()
        properties.putAll([key1: "value1", key2: "value2"])
        def propertiesFactory = build().factory().fromProduct(properties)

        // when
        def factory = build().keyValueStoreFactory().fromPropertiesFactory(propertiesFactory)

        // then
        assert asMap(factory.produce()) == properties
    }

    @Test
    void shallFilterKeyValueStore() {
        // given
        def sourceMap = [a1: 'v1', a2: 'v2', b1: 'v3']
        def sourceFactory = build().factory().fromProduct(sourceMap as InMemoryKeyValueStore)
        def pattern = ~/a.*/

        // when
        def factory = build().keyValueStoreFactory().filter(sourceFactory, pattern)

        // then
        assert asMap(factory.produce()) == sourceMap.findAll { k, v -> k =~ pattern }
    }

    @Test
    void shallMergeKeyValueStore() {
        // given
        def sourceMap1 = [a1: 'v1', a2: 'v2']
        def sourceFactory1 = build().factory().fromProduct(sourceMap1 as InMemoryKeyValueStore)

        def sourceMap2 = [a2: 'v2', a3: 'v3']
        def sourceFactory2 = build().factory().fromProduct(sourceMap2 as InMemoryKeyValueStore)

        // when
        def factory = build().keyValueStoreFactory().merge(sourceFactory1, sourceFactory2)

        // then
        assert asMap(factory.produce()) == (sourceMap1 + sourceMap2)
    }

}
