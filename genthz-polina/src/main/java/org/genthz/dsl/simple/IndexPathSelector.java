package org.genthz.dsl.simple;

import org.genthz.context.Context;
import org.genthz.context.PositionedContext;
import org.genthz.dsl.Selector;

import java.util.Objects;

public class IndexPathSelector extends PathSelector {
    private final int index;

    public IndexPathSelector(Selector parent, int index) {
        super(Objects.requireNonNull(parent));
        this.index = index;
    }

    @Override
    public boolean test(Context<?, ?, ?> context) {
        final boolean result;
        if (context instanceof PositionedContext) {
            return this.index == ((PositionedContext<?, ?, ?>) context).position() && super.test(context);
        } else {
            result = false;
        }

        return result;
    }
}