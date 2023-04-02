package org.genthz.context;

import java.lang.reflect.Constructor;

public interface ConstructorContext<T, P extends Context<?, ?, ?>, N>
        extends Context<T, P, N>, Instance<T>, Accessor<T> {
    public Constructor<T> constructor();

    public ConstructorContext<T, P, N> constructor(Constructor<T> constructor);
}