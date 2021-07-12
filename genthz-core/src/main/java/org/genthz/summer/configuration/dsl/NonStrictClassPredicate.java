package org.genthz.summer.configuration.dsl;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.genthz.Context;

import java.util.function.Predicate;

class StrictClassPredicate<T> implements Predicate<Context<?>> {
    private final Class<T> clazz;

    public StrictClassPredicate(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public boolean test(Context<?> context) {
        return clazz.equals(context.clazz());
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
