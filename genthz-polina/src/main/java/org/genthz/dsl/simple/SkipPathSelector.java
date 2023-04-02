package org.genthz.dsl.simple;

import org.genthz.context.Context;
import org.genthz.dsl.Selector;

class SkipPathSelector extends AbstractSelector {
    private final int skip;

    public SkipPathSelector(Selector parent, int skip) {
        super(parent);
        this.skip = skip;
    }

    @Override
    public boolean test(Context<?, ?, ?> context) {
        final boolean result;
        final Context[] ups = context.parents().toArray(i -> new Context[i]);

        if (ups.length >= this.skip) {
            result = this.up().map(e -> e.test(ups[ups.length - this.skip])).orElse(false);
        } else {
            result = false;
        }

        return result;
    }
}
