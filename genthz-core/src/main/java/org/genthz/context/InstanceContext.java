package org.genthz.context;

import java.lang.reflect.Type;

public interface InstanceContext<T> extends Accessor<T>, Instance<T>, Context {
    public Type type();
}