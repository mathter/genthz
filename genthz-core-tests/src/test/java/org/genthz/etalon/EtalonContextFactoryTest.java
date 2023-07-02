package org.genthz.etalon;

import org.apache.commons.lang3.reflect.TypeUtils;
import org.genthz.context.Bindings;
import org.genthz.context.ContextFactory;
import org.genthz.context.InstanceContext;
import org.genthz.context.NodeInstanceContext;
import org.genthz.etalon.model.SimpleGeneric;
import org.genthz.etalon.model.SimpleGeneric2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

public abstract class EtalonContextFactoryTest {
    @Test
    public void testSingle() {
        final InstanceContext<SimpleGeneric> context = this.contextFactory().single(SimpleGeneric.class, String.class, Integer.class);

        Assertions.assertNotNull(context);
        Assertions.assertEquals(
                TypeUtils.parameterize(SimpleGeneric.class, String.class, Integer.class),
                context.type()
        );
    }

    @Test
    public void testSingleWithBindings() {
        final Bindings bindings = Bindings.bindings("parameter", "parameter value");
        final InstanceContext<SimpleGeneric> context = this.contextFactory().single(bindings, SimpleGeneric.class, String.class, Integer.class);

        Assertions.assertNotNull(context);
        Assertions.assertNotNull(context.bindings());
        Assertions.assertEquals(
                TypeUtils.parameterize(SimpleGeneric.class, String.class, Integer.class),
                context.type()
        );
    }

    @Test
    public void testSingleWithoutTypeArguments() {
        final InstanceContext<SimpleGeneric> context = this.contextFactory().single(SimpleGeneric.class);

        Assertions.assertNotNull(context);
        Assertions.assertEquals(
                TypeUtils.parameterize(SimpleGeneric.class, Object.class, Object.class),
                context.type()
        );
    }

    @Test
    public void testByConstructor() {
        final InstanceContext<SimpleGeneric2> up = this.contextFactory().single(SimpleGeneric2.class, String.class, Integer.class);
        final List<NodeInstanceContext<?, Integer>> contexts = this.contextFactory().byConstructor(
                up,
                SimpleGeneric2.class.getDeclaredConstructors()[0]
        );

        Assertions.assertNotNull(contexts);
        Assertions.assertEquals(4, contexts.size());

        Assertions.assertEquals(0, contexts.get(0).node());
        Assertions.assertEquals(String.class, contexts.get(0).type());

        Assertions.assertEquals(1, contexts.get(1).node());
        Assertions.assertEquals(Integer.class, contexts.get(1).type());

        Assertions.assertEquals(2, contexts.get(2).node());
        Assertions.assertEquals(TypeUtils.parameterize(List.class, String.class), contexts.get(2).type());

        Assertions.assertEquals(3, contexts.get(3).node());
        Assertions.assertEquals(TypeUtils.parameterize(Map.class, String.class, Integer.class), contexts.get(3).type());
    }

    protected abstract ContextFactory contextFactory();
}
