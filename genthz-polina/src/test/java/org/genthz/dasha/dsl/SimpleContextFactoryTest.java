package org.genthz.dasha.dsl;

import org.genthz.context.ContextFactory;
import org.genthz.context.InstanceContext;
import org.genthz.dasha.context.DashaContextFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;

public class SimpleContextFactoryTest {
    private ContextFactory factory = new DashaContextFactory();

    @Test
    public void test() {
        final InstanceContext<C0, ?> objectContext = this.factory.context(C0.class);
        Assertions.assertNotNull(objectContext);

        final Collection<InstanceContext> contexts = this.factory.contexts(objectContext);
        Assertions.assertNotNull(contexts);
        Assertions.assertEquals(1, contexts.size());
    }

    @Test
    public void testGeneric() {
        final InstanceContext<C1, ?> objectContext = this.factory.context(C1.class, String.class, Double.class);
        Assertions.assertNotNull(objectContext);

        final Collection<InstanceContext> fieldContexts = this.factory.contexts(objectContext);
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
