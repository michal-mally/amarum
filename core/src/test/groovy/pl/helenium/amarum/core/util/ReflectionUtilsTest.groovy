package pl.helenium.amarum.core.util

import org.testng.annotations.DataProvider
import org.testng.annotations.Test

import static pl.helenium.amarum.core.util.ReflectionUtils.flattenVarargs

class ReflectionUtilsTest {

    @DataProvider
    Object[][] arraysToFlatten() {
        [
                [ [] , [] ],
                [ ['a'] , ['a'] ],
                [ ['a', []] , ['a'] ],
                [ ['a', ['b']] , ['a', 'b'] ],
                [ [['a'], 'c', ['b']] , [['a'], 'c', 'b'] ],
        ]
    }

    @Test(dataProvider = "arraysToFlatten")
    void shallFlattenCorrectly(Object[] input, Object[] output) {
        assert flattenVarargs(input) == output
    }

}
