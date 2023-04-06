package org.genthz.dsl.simple;

import org.genthz.context.ContextFactory;
import org.genthz.context.ConstructorContext;
import org.genthz.context.ObjectContext;
import org.genthz.simple1.SimpleContextFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;

public class SimpleContextFactoryTest {
    private ContextFactory factory = new SimpleContextFactory();

    @Test
    public void test() {
        final ObjectContext<C0, ?> objectContext = this.factory.contexts(C0.class);
        Assertions.assertNotNull(objectContext);

        final Collection<ConstructorContext<?, ?, ?>> contexts = this.factory.contexts(objectContext);
        Assertions.assertNotNull(contexts);
        Assertions.assertEquals(1, contexts.size());
    }

    @Test
    public void testGeneric() {
        final ObjectContext<C1, ?> objectContext = this.factory.contexts(C1.class, String.class, Double.class);
        Assertions.assertNotNull(objectContext);

        final Collection<ConstructorContext<?, ?, ?>> fieldContexts = this.factory.contexts(objectContext);
        Assertions.assertNotNull(fieldContexts);
        Assertions.assertEquals(2, fieldContexts.size());
    }

    public static class C0 {
        private String field;
    }

    public static class C1<X, Y> {
        private X x;

        private List<Y> y;
    }
}
