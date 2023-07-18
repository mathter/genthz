package org.genthz.function;

import org.genthz.context.InstanceContext;

import java.lang.reflect.Type;
import java.util.Random;

public class DefaultEnumBuilder<T extends Enum<T>> implements InstanceBuilder<T> {
    private static final Random RANDOM = new Random();

    @Override
    public T instance(InstanceContext<T> context) {
        final T result;
        final Type type = context.type();

        if (type instanceof Class && ((Class<T>) type).isEnum()) {
            final T[] values = ((Class<T>) type).getEnumConstants();
            result = values[RANDOM.nextInt(values.length)];
        } else {
            throw new IllegalStateException(
                    String.format("%s is not an java.lang.Enum. Context: %s", type, context)
            );
        }

        return result;
    }
}
