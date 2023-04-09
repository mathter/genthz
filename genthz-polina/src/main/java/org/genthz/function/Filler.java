package org.genthz.function;

import org.genthz.context.Context;

@FunctionalInterface
public interface Filler<T> {
    public void fill(Context context);
}
