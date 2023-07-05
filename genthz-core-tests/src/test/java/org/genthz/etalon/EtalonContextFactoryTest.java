package org.genthz.etalon;

import org.apache.commons.lang3.reflect.TypeUtils;
import org.genthz.context.AccessorResolver;
import org.genthz.context.Bindings;
import org.genthz.context.ContextFactory;
import org.genthz.context.InstanceContext;
import org.genthz.context.Node;
import org.genthz.context.NodeInstanceContext;
import org.genthz.etalon.model.SimpleGeneric;
import org.genthz.etalon.model.SimpleGeneric2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    @Test
    public void testByCollection() {
        final InstanceContext<List> up = this.contextFactory().single(List.class, String.class);
        final List<NodeInstanceContext<String, Integer>> contexts = this.contextFactory().<List, String>byCollection(up, 10);

        Assertions.assertNotNull(contexts);
        Assertions.assertEquals(10, contexts.size());

        for (int i = 0; i < contexts.size(); i++) {
            final NodeInstanceContext<String, Integer> context = contexts.get(i);
            Assertions.assertNotNull(context);
            Assertions.assertEquals(String.class, context.type());
            Assertions.assertNotNull(context.node());
        }
    }

    @Test
    public void testByArray() {
        final InstanceContext<String[]> up = this.contextFactory().single(String[].class);
        final List<NodeInstanceContext<String, Integer>> contexts = this.contextFactory().byArray(up, 10);

        Assertions.assertNotNull(contexts);
        Assertions.assertEquals(10, contexts.size());

        for (int i = 0; i < contexts.size(); i++) {
            final NodeInstanceContext<String, Integer> context = contexts.get(i);
            Assertions.assertNotNull(context);
            Assertions.assertEquals(String.class, context.type());
            Assertions.assertNotNull(context.node());
        }
    }

    @Test
    public void testByProperties() {
        final InstanceContext<SimpleGeneric> up = this.contextFactory().single(SimpleGeneric.class, String.class, Integer.class);
        final Collection<NodeInstanceContext<?, String>> contexts = this.contextFactory().byProperties(up);

        Assertions.assertNotNull(contexts);
        Assertions.assertEquals(8, contexts.size());

        final NodeInstanceContext<?, String>[] ctxs = contexts.stream()
                .sorted(Comparator.comparing(Node::node))
                .toArray(NodeInstanceContext[]::new);

        Assertions.assertNotNull(ctxs[0]);
        Assertions.assertEquals("arrayField", ctxs[0].node());
        Assertions.assertEquals(TypeUtils.genericArrayType(Integer.class), ctxs[0].type());

        Assertions.assertNotNull(ctxs[1]);
        Assertions.assertEquals("collectionField", ctxs[1].node());
        Assertions.assertEquals(TypeUtils.parameterize(Collection.class, Integer.class), ctxs[1].type());

        Assertions.assertNotNull(ctxs[2]);
        Assertions.assertEquals("dateField", ctxs[2].node());
        Assertions.assertEquals(Date.class, ctxs[2].type());

        Assertions.assertNotNull(ctxs[3]);
        Assertions.assertEquals("listField", ctxs[3].node());
        Assertions.assertEquals(TypeUtils.parameterize(List.class, Integer.class), ctxs[3].type());

        Assertions.assertNotNull(ctxs[4]);
        Assertions.assertEquals("mapField", ctxs[4].node());
        Assertions.assertEquals(TypeUtils.parameterize(Map.class, String.class, Integer.class), ctxs[4].type());

        Assertions.assertNotNull(ctxs[5]);
        Assertions.assertEquals("setField", ctxs[5].node());
        Assertions.assertEquals(TypeUtils.parameterize(Set.class, Integer.class), ctxs[5].type());

        Assertions.assertNotNull(ctxs[6]);
        Assertions.assertEquals("stringField", ctxs[6].node());
        Assertions.assertEquals(String.class, ctxs[6].type());

        Assertions.assertNotNull(ctxs[7]);
        Assertions.assertEquals("tField", ctxs[7].node());
        Assertions.assertEquals(Integer.class, ctxs[7].type());
    }

    @ParameterizedTest
    @MethodSource("data")
    public void testByProperties(Collection<String> includes, Collection<String> excludes, Set<String> fiedldNames) {
        final InstanceContext<SimpleGeneric> up = this.contextFactory().single(SimpleGeneric.class, String.class, Integer.class);
        final AccessorResolver accessorResolver = this.accessorResolver(includes, excludes);
        final Collection<NodeInstanceContext<?, String>> contexts = this.contextFactory().byProperties(up, accessorResolver);

        Assertions.assertNotNull(contexts);
        Assertions.assertEquals(fiedldNames.size(), contexts.size());

        for (NodeInstanceContext<?, String> ctx : contexts) {
            Assertions.assertTrue(fiedldNames.remove(ctx.node()), "Error for " + ctx);
        }

        Assertions.assertTrue(fiedldNames.isEmpty());
    }

    public static Stream<Arguments> data() {
        final String[] fieldNames = {"stringField", "dateField", "tField", "collectionField", "listField", "setField", "mapField", "arrayField"};
        return Stream.concat(
                Stream.of(
                        Arguments.of(
                                null,
                                null,
                                Stream.of(fieldNames).collect(Collectors.toSet())
                        )
                ),
                Stream.concat(
                        Stream.of(fieldNames)
                                .reduce(
                                        new ArrayList<Set<String>>(),
                                        (l, r) -> {
                                            final Set<String> max = l.stream().max(Comparator.comparingInt(Set::size)).orElse(new HashSet<>());
                                            final Set<String> next = new HashSet<String>();

                                            next.addAll(max);
                                            next.add(r);

                                            l.add(next);
                                            return l;
                                        },
                                        (l, r) -> {
                                            l.addAll(r);
                                            return l;
                                        }
                                )
                                .stream()
                                .map(e -> Arguments.of(
                                                e,
                                                null,
                                                e
                                        )
                                ),
                        Stream.of(fieldNames)
                                .reduce(
                                        new ArrayList<Set<String>>(),
                                        (l, r) -> {
                                            final Set<String> max = l.stream().max(Comparator.comparingInt(Set::size)).orElse(new HashSet<>());
                                            final Set<String> next = new HashSet<String>();

                                            next.addAll(max);
                                            next.add(r);

                                            l.add(next);
                                            return l;
                                        },
                                        (l, r) -> {
                                            l.addAll(r);
                                            return l;
                                        }
                                )
                                .stream()
                                .map(e -> Arguments.of(
                                                null,
                                                e,
                                                Optional.of(Stream.of(fieldNames).collect(Collectors.toSet()))
                                                        .map(ee -> {
                                                            ee.removeAll(e);
                                                            return ee;
                                                        })
                                                        .get()
                                        )
                                )
                )
        );
    }

    protected abstract ContextFactory contextFactory();

    protected abstract AccessorResolver accessorResolver(Collection<String> includes, Collection<String> excludes);
}
