package org.genthz.simple;

import org.genthz.context.ContextFactory;
import org.genthz.context.Context;
import org.genthz.context.Instance;

import java.lang.reflect.Constructor;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.Map;

abstract class IndexedContext<T, PT, P extends Context<?, ?, ?> & Instance<PT>>
        extends ConstructorContext<T, P, Integer>
        implements org.genthz.context.IndexedContext<T, PT, P> {

    private final int position;

    public IndexedContext(ContextFactory contextFactory,
                          P parent,
                          Type type,
                          Map<TypeVariable, Type> genericAtribution,
                          Constructor<T> constructor,
                          int position) {
        super(contextFactory, parent, type, genericAtribution, constructor);
        this.position = position;
    }

    @Override
    public int position() {
        return this.position;
    }
}
