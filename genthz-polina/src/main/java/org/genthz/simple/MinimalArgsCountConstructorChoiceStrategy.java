package org.genthz.simple;

import org.genthz.ConstructorChoiceStrategy;
import org.genthz.reflection.GenericUtil;

import java.lang.reflect.Constructor;
import java.lang.reflect.Type;
import java.util.Objects;
import java.util.stream.Stream;

public class MinimalArgsCountConstructorChoiceStrategy implements ConstructorChoiceStrategy {
    @Override
    public <T> Constructor<T> constructor(Type type) {
        final Class<T> clazz = GenericUtil.rawType(type);

        return clazz.isInterface()
                ? null
                : (Constructor<T>) Stream.of(Objects.requireNonNull(clazz).getDeclaredConstructors())
                .min((left, right) -> left.getParameterCount() - right.getParameterCount())
                .get();
    }
}
