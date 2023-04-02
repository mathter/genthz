package org.genthz.simple;

import org.genthz.context.ConstructorContext;
import org.genthz.context.ContextFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.Map;

class ConstructorArgContext<T, PT, P extends ConstructorContext<PT, ?, ?>> extends IndexedContext<T, PT, P> {
    private T object;

    public ConstructorArgContext(ContextFactory contextFactory,
                                 P parent,
                                 Type type,
                                 Map<TypeVariable, Type> genericAtribution,
                                 Constructor<T> constructor,
                                 int position) {
        super(contextFactory, parent, type, genericAtribution, constructor, position);
    }

    @Override
    public T get() {
        return this.object;
    }

    @Override
    public void set(T value) {
        this.object = value;
    }

    @Override
    public T instance() {
        return this.object;
    }

    @Override
    public Integer node() {
        return this.position();
    }
}
