package org.genthz.dasha.dsl;

import org.genthz.context.Context;
import org.genthz.context.InstanceContext;
import org.genthz.function.Selector;

class SkipPathSelector extends PathSelector {
    private final int skip;

    public SkipPathSelector(Selector parent, int skip) {
        super(parent);
        this.skip = skip;
        this.metric(skip);
    }

    public int getSkip() {
        return skip;
    }

    @Override
    public boolean test(Context context) {
        final boolean result;
        final InstanceContext[] ups = context.ups()
                .filter(e -> e instanceof InstanceContext)
                .toArray(i -> new InstanceContext[i]);

        if (ups.length >= this.skip) {
            result = this.up().map(e -> e.test(ups[ups.length - this.skip])).orElse(false);
        } else {
            result = false;
        }

        return result;
    }
}
