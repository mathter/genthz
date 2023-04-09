package org.genthz.context;

import java.lang.reflect.Type;

public interface InstanceContext<T, N> extends Accessor<T>, Instance<T>, Node<N>, Context {
    public Type type();

public <L, LN> InstanceContext<L, LN> left();
}