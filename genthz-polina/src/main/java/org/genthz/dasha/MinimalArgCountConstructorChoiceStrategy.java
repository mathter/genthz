package org.genthz.dasha;

import org.genthz.ConstructorChoiceStrategy;
import org.genthz.reflection.GenericUtil;

import java.lang.reflect.Constructor;
import java.lang.reflect.Type;
import java.util.stream.Stream;

public class MinimalArgCountConstructorChoiceStrategy implements ConstructorChoiceStrategy {
    @Override
    public <T> Constructor<T> constructor(Type type) {
        final Constructor<T> result;
        final Class<T> clazz = GenericUtil.rawType(type);

        if (clazz.isInterface()) {
            result = null;
        } else {
            result = (Constructor<T>) Stream.of(clazz.getDeclaredConstructors())
                    .min((l, r) -> l.getParameterCount() - r.getParameterCount())
                    .get();
        }

        return result;
    }
}
