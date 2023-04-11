package org.genthz.function;

import org.genthz.context.InstanceContext;

@FunctionalInterface
public interface Filler<T> {
    public void fill(InstanceContext<T, ?> context);
}
