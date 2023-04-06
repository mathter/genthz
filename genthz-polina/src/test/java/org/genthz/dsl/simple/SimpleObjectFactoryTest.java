package org.genthz.dsl.simple;

import org.genthz.ObjectFactory;
import org.genthz.simple1.SimpleObjectFactory;
import org.junit.jupiter.api.Test;

public class SimpleObjectFactoryTest {
    @Test
    public void test() {
        ObjectFactory objectFactory = new SimpleObjectFactory();
        String s = objectFactory.get(String.class);

        System.out.println(s);
    }
}
