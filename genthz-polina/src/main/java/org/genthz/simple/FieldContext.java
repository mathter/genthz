package org.genthz.simple;

import org.genthz.context.ContextFactory;
import org.genthz.context.Instance;
import org.genthz.reflection.Util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.Map;

import org.genthz.context.ConstructorContext;

class FieldContext<T, PT, P extends ConstructorContext<PT, ?, ?> & Instance<PT>>
        extends org.genthz.simple.ConstructorContext<T, P, String>
        implements org.genthz.context.FieldContext<T, PT, P> {
    private final Field field;

    public FieldContext(ContextFactory contextFactory,
                        P parent,
                        Type type,
                        Map<TypeVariable, Type> genericAtribution,
                        Constructor<T> constructor,
                        Field field) {
        super(contextFactory, parent, type, genericAtribution, constructor);
        this.field = field;
    }

    @Override
    public T get() {
        return Util.getFieldValue(this.field, this.parent().instance());
    }

    @Override
    public void set(T value) {
        Util.setFieldValue(this.field, this.parent().instance(), value);
    }

    @Override
    public T instance() {
        return this.get();
    }

    @Override
    public String node() {
        return this.field.getName();
    }
}
