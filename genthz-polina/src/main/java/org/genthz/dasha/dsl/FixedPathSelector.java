package org.genthz.dasha.dsl;

import org.genthz.context.Context;
import org.genthz.context.InstanceContext;
import org.genthz.function.Selector;

class FixedPathSelector extends PathSelector {
    private final String element;

    public FixedPathSelector(Selector parent, String element) {
        super(parent);
        this.element = element;
    }

    public String getElement() {
        return element;
    }

    @Override
    public boolean test(Context context) {
        boolean result;
        if (context instanceof InstanceContext) {
            // Check for root
            if ("/".equals(this.element)) {
                result = context.up() == null;
            } else {
                result = this.element.equals(((InstanceContext) context).node());
            }
        } else {
            result = false;
        }

        return result && super.test(context);
    }
}
