package org.genthz.simple;

import org.genthz.context.ContextFactory;
import org.genthz.context.Context;
import org.genthz.context.Instance;

import java.lang.reflect.Constructor;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.Map;

abstract class ConstructorContext<T, P extends Context<?, ?, ?>, N>
        extends AbstractContext<T, P, N>
        implements org.genthz.context.ConstructorContext<T, P, N>, Instance<T> {
    private Constructor<T> constructor;

    public ConstructorContext(ContextFactory contextFactory,
                              P parent,
                              Type type,
                              Map<TypeVariable, Type> genericAtribution,
                              Constructor<T> constructor
    ) {
        super(contextFactory, parent, type, genericAtribution);
        this.constructor = constructor;
    }

    @Override
    public Constructor<T> constructor() {
        return this.constructor;
    }

    @Override
    public org.genthz.context.ConstructorContext<T, P, N> constructor(Constructor<T> constructor) {
        this.constructor = constructor;
        return this;
    }
}
