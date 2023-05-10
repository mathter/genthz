package org.genthz.dasha.dsl;

import org.genthz.context.Context;
import org.genthz.function.Selector;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

abstract class TypeSelector extends AbstractSelector {
    protected final Type type;

    public TypeSelector(Selector parent, Type type) {
        super(parent);
        this.type = type;
    }

    @Override
    public boolean test(Context context) {
        return this.up().map(e -> e.test(context)).orElse(true);
    }

    protected Type down(Type type) {
        final Type result;

        if (type instanceof Class) {
            result = type;
        } else if (type instanceof ParameterizedType && ((ParameterizedType) type).getActualTypeArguments().length == 0) {
            result = ((ParameterizedType) type).getRawType();
        } else {
            result = type;
        }

        return result;
    }
}
