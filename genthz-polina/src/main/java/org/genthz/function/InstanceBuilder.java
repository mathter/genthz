package org.genthz.function;

import org.genthz.context.InstanceContext;

@FunctionalInterface
public interface InstanceBuilder<T> {
    public T instance(InstanceContext<T, ?> context);
}
