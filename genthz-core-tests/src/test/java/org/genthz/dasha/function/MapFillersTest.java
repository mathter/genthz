package org.genthz.dasha.function;

import org.apache.commons.lang3.RandomUtils;
import org.genthz.ObjectFactory;
import org.genthz.dasha.dsl.DashaDsl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class MapFillersTest {
    @Test
    public void test() {
        final int size = RandomUtils.nextInt(10, 100);
        final ObjectFactory objectFactory = new DashaDsl() {
            {
                this.def();
                this.strict(Map.class, String.class, Integer.class)
                        .filler(MapFillers.size(size));
            }
        }.objectFactory();
        final Map<String, Integer> instance = objectFactory.get(Map.class, String.class, Integer.class);

        Assertions.assertNotNull(instance);
        Assertions.assertEquals(size, instance.size());
    }
}
