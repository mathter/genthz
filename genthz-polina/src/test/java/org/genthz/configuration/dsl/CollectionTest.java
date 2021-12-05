package org.genthz.configuration.dsl;

import org.genthz.ObjectFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collection;

public class CollectionTest {
    @Test
    public void test() {
        final ObjectFactory objectFactory = DslFactory.get().dsl().objectFactory();
        final Collection<String> c = objectFactory.build(Collection.class);

        Assertions.assertNotNull(c);
    }
}
