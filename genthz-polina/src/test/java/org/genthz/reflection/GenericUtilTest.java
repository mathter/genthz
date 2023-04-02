package org.genthz.reflection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

public class GenericUtilTest {
    @ParameterizedTest
    @MethodSource("data0")
    public void test(Type origin, Class<?> target) {
        final Class<Entity> clazz = GenericUtil.rawType(origin);
        Assertions.assertNotNull(clazz);
        Assertions.assertEquals(target, clazz);
    }

    private static Stream<Arguments> data0() throws Throwable {
        return Stream.of(
                Arguments.of(Entity.class, Entity.class),
                Arguments.of(Entity.class.getDeclaredField("f0").getGenericType(), Object.class),
                Arguments.of(Entity.class.getDeclaredField("f1").getGenericType(), List.class),
                Arguments.of(Entity.class.getDeclaredField("f2").getGenericType(), List.class),
                Arguments.of(Entity.class.getDeclaredField("f3").getGenericType(), Object[].class),
                Arguments.of(Entity.class.getDeclaredField("f4").getGenericType(), List.class),
                Arguments.of(Entity.class.getDeclaredField("f5").getGenericType(), List.class)
        );
    }

    @Test
    public void testAtribution() {
        final Map<TypeVariable, Type> map = GenericUtil.attribution(Entity.class, String.class, Integer.class, Entity.class.getTypeParameters()[0]);
        Assertions.assertNotNull(map);
        Assertions.assertEquals(3, map.size());
        Assertions.assertEquals(String.class, map.get(Entity.class.getTypeParameters()[0]));
        Assertions.assertEquals(Integer.class, map.get(Entity.class.getTypeParameters()[1]));
        Assertions.assertEquals(Entity.class.getTypeParameters()[0], map.get(Entity.class.getTypeParameters()[2]));
    }

    @Test
    public void testResolve0() {
        final Map<TypeVariable, Type> map = GenericUtil.attribution(Entity.class, String.class, Integer.class, Entity.class.getTypeParameters()[0]);
        final Type type = GenericUtil.resolve(Entity.class, map);
        Assertions.assertNotNull(type);
        Assertions.assertTrue(type instanceof ParameterizedType);
        final ParameterizedType ptype = (ParameterizedType) type;
        final Type[] actualTypeArgument = ptype.getActualTypeArguments();
        Assertions.assertTrue(actualTypeArgument[0] instanceof Class);
        Assertions.assertEquals(String.class, actualTypeArgument[0]);

        Assertions.assertTrue(actualTypeArgument[1] instanceof Class);
        Assertions.assertEquals(Integer.class, actualTypeArgument[1]);

        Assertions.assertTrue(actualTypeArgument[2] instanceof Class);
        Assertions.assertEquals(String.class, actualTypeArgument[2]);
    }

    @Test
    public void testResolve1() {
        final Map<TypeVariable, Type> map = GenericUtil.attribution(String.class);
        Assertions.assertNotNull(map);
        Assertions.assertEquals(0, map.size());
    }

    @Test
    public void testResolve2() {
        final Map<TypeVariable, Type> map = GenericUtil.attribution(SubEntity.class);
        Assertions.assertNotNull(map);
        Assertions.assertEquals(0, map.size());
    }

    public static class Entity<X, Y, Z> {
        private X f0;

        private List<Y> f1;

        private List<Set<Y>> f2;

        private Z[] f3;

        private List<Z[]> f4;

        private List<Set<Z[]>> f5;
    }

    public static class SubEntity extends Entity<Short, Integer, String> {
    }
}
