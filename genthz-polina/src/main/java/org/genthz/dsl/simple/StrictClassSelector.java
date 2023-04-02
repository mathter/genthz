package org.genthz.dsl.simple;

import org.genthz.context.Context;
import org.genthz.dsl.Selector;

import java.lang.reflect.Type;

class StrictClassSelector extends TypeSelector {
    public StrictClassSelector(Selector parent, Type type) {
        super(parent, type);
    }

    @Override
    public boolean test(Context<?, ?, ?> context) {
        return this.type.equals(context.type()) && super.test(context);
    }
}
