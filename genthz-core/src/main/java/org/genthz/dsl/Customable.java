package org.genthz.dsl;

import org.genthz.context.InstanceContext;

import java.util.function.Predicate;

public interface Customable {
    default public <T, S extends Pathable & Strictable & Unstricable & InstanceBuilderFirst<T> & FillerFirst<T> & Metric<S> & Using<S>> S c(Predicate<InstanceContext<T>> predicate) {
        return this.custom(predicate);
    }

    public <T, S extends Pathable & Strictable & Unstricable & InstanceBuilderFirst<T> & FillerFirst<T> & Metric<S> & Using<S>> S custom(Predicate<InstanceContext<T>> predicate);
}
