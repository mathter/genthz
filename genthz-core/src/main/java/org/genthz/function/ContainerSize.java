package org.genthz.function;

import org.genthz.context.Context;

@FunctionalInterface
public interface ContainerSize<T extends Context> {
    public int get(T context);
}
