package org.genthz.dsl.simple;


import org.genthz.context.Context;
import org.genthz.dsl.Selector;

public class PathSelector extends AbstractSelector {

    public PathSelector(Selector parent) {
        super(parent);
    }

    @Override
    public boolean test(Context<?, ?, ?> context) {
        return this.up()
                .map(e -> context.parent() != null ? e.test(context.parent()) : false)
                .orElse(true);
    }
}
