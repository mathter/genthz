package org.genthz.simple;

import org.genthz.context.ContextFactory;
import org.genthz.context.Bindings;
import org.genthz.context.Context;

import java.lang.reflect.Constructor;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.Map;

class ObjectContext<T, P extends Context<Void, ?, Void>>
        extends ConstructorContext<T, P, Void>
        implements org.genthz.context.ObjectContext<T, P> {
    private T object;

    public ObjectContext(ContextFactory contextFactory,
                         Bindings bindings,
                         P parent,
                         Type type,
                         Map<TypeVariable, Type> genericAtribution,
                         Constructor<T> constructor) {
        super(contextFactory, parent, type, genericAtribution, constructor);
    }

    @Override
    public T get() {
        return this.object;
    }

    @Override
    public void set(T value) {
        this.object = object;
    }

    @Override
    public T instance() {
        return this.object;
    }

    @Override
    public Void node() {
        return null;
    }
}
