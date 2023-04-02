package org.genthz.function;

import org.genthz.context.ConstructorContext;

@FunctionalInterface
public interface Filler<T> {
    public void fill(ConstructorContext<T, ?, ?> context);
}
