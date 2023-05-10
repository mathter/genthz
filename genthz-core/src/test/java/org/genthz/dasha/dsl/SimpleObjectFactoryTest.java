package org.genthz.dasha.dsl;

import org.genthz.ObjectFactory;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class SimpleObjectFactoryTest {
    @Test
    @Disabled
    public void test() {
        ObjectFactory objectFactory = null;
        String s = objectFactory.get(String.class);

        System.out.println(s);
    }
}
