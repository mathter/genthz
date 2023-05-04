package org.genthz.dasha.context;

import org.apache.commons.lang3.reflect.TypeUtils;
import org.genthz.ConstructorChoiceStrategy;

import java.lang.reflect.Constructor;
import java.lang.reflect.Type;
import java.util.stream.Stream;

public class MinimalArgCountConstructorChoiceStrategy implements ConstructorChoiceStrategy {
    @Override
    public <T> Constructor<T> constructor(Type type) {
        final Constructor<T> result;
        final Class<T> clazz = (Class<T>) TypeUtils.getRawType(type, Object.class);

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
