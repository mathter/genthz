package org.genthz.function;

import org.genthz.context.ConstructorContext;

@FunctionalInterface
public interface InstanceBuilder<T> {
    public T instance(ConstructorContext<T, ?, ?> context);
}
