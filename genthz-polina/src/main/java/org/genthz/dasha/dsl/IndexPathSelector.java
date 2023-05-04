package org.genthz.dasha.dsl;

import org.genthz.context.Context;
import org.genthz.context.InstanceContext;
import org.genthz.context.NodeInstanceContext;
import org.genthz.function.Selector;

import java.util.Objects;

public class IndexPathSelector extends PathSelector {
    private final int index;

    public IndexPathSelector(Selector parent, int index) {
        super(Objects.requireNonNull(parent));
        this.index = index;
    }

    @Override
    public boolean test(Context context) {
        final boolean result;

        if (context instanceof NodeInstanceContext) {
            final Object node = ((NodeInstanceContext) context).node();
            if (node instanceof Integer) {
                return this.index == (Integer) node && super.test(context);
            } else {
                result = false;
            }
        } else {
            result = false;
        }

        return result;
    }
}
