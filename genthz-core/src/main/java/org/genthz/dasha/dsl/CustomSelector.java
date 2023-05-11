package org.genthz.dasha.dsl;

import org.genthz.context.Context;
import org.genthz.function.Selector;

import java.util.Objects;
import java.util.function.Predicate;

class CustomSelector extends AbstractSelector {
    private final Predicate<Context> predicate;

    public CustomSelector(Selector parent, Predicate<Context> predicate) {
        super(parent);
        this.predicate = Objects.requireNonNull(predicate);
    }

    @Override
    public boolean test(Context context) {
        return this.predicate.test(context);
    }
}