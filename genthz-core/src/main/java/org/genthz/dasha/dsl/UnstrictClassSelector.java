package org.genthz.dasha.dsl;

import org.apache.commons.lang3.reflect.TypeUtils;
import org.genthz.context.Context;
import org.genthz.context.InstanceContext;
import org.genthz.function.Selector;

import java.lang.reflect.Type;

class UnstrictClassSelector extends TypeSelector {
    public UnstrictClassSelector(Selector parent, Type type) {
        super(parent, type);
    }

    @Override
    public boolean test(Context context) {

        return context instanceof InstanceContext
                && TypeUtils.isAssignable(this.down(((InstanceContext<?>) context).type()), this.down(this.type))
                && super.test(context);
    }
}
