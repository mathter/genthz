package org.genthz.dsl;

import org.genthz.context.Context;

import java.util.function.Predicate;

public interface Customable {
    default public <S extends Pathable & Strictable & Unstricable & InstanceBuilderFirst & FillerFirst> S c(Predicate<Context> predicate) {
        return this.custom(predicate);
    }

    public <S extends Pathable & Strictable & Unstricable & InstanceBuilderFirst & FillerFirst> S custom(Predicate<Context> predicate);
}
