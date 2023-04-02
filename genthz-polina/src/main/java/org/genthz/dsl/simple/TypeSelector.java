package org.genthz.dsl.simple;

import org.genthz.context.Context;
import org.genthz.dsl.Selector;

import java.lang.reflect.Type;

abstract class TypeSelector extends Able {
    protected final Type type;

    public TypeSelector(Selector parent, Type type) {
        super(parent);
        this.type = type;
    }

    @Override
    public boolean test(Context<?, ?, ?> context) {
        return this.up().map(e -> e.test(context)).orElse(true);
    }
}
