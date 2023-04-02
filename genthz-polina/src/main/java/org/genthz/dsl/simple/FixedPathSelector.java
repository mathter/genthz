package org.genthz.dsl.simple;

import org.genthz.context.Context;
import org.genthz.dsl.Selector;

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
    public boolean test(Context<?, ?, ?> context) {
        boolean result;

        // Check for root
        if ("/".equals(this.element)) {
            result = context.parent() == null;
        } else {
            result = this.element.equals(context.node());
        }

        return result && super.test(context);
    }
}
