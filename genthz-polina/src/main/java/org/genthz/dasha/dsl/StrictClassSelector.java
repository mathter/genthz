package org.genthz.dasha.dsl;

import org.genthz.context.Context;
import org.genthz.context.InstanceContext;
import org.genthz.function.Selector;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Objects;

class StrictClassSelector extends TypeSelector {
    public StrictClassSelector(Selector parent, Type type) {
        super(parent, type);
    }

    @Override
    public boolean test(Context context) {
        return context instanceof InstanceContext
                && Objects.equals(this.down(this.type), this.down(((InstanceContext<?>) context).type()))
                && super.test(context);
    }
}
