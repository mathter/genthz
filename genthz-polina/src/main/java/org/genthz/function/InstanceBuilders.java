package org.genthz.function;

import java.lang.reflect.Constructor;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Stream;

public final class InstanceBuilders {
    public static <T> InstanceBuilder<T> constructor(Constructor<? extends T> constructor) {
        return new ConstructorInstanceBuilder(constructor);
    }

    public static <T> InstanceBuilder<T> constructor(Class<? super T> clazz, Predicate<Constructor<T>> predicate) {
        return InstanceBuilders.constructor(Stream.of(clazz.getConstructors())
                .map(e -> (Constructor<T>) e)
                .filter(predicate)
                .findFirst()
                .get()
        );
    }

    public static <T> InstanceBuilder<T> constructorWithMinArgs(Class<? extends T> clazz) {
        final int minArgsCount = Stream.of(clazz.getConstructors())
                .map(Constructor::getParameterCount)
                .min(Integer::compareTo)
                .get();

        return InstanceBuilders.constructor((Class<? super T>) clazz, e -> e.getParameterCount() == minArgsCount);
    }

    private InstanceBuilders() {
    }

    public static void main(String[] args) {
        InstanceBuilder<Set> ib = InstanceBuilders.constructorWithMinArgs(HashSet.class);
    }
}
