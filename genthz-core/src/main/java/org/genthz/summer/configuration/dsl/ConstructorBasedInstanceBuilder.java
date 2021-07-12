package org.genthz.function;

import org.genthz.context.Context;
import org.genthz.NewInstanceException;
import org.genthz.Util;
import org.genthz.configuration.dsl.Selector;

import java.lang.reflect.Constructor;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class ConstructorBasedInstanceBuilder<T>
        extends FunctionalInstanceBuilder<T>
        implements org.genthz.configuration.dsl.ConstructorBasedInstanceBuilder<T> {

    private final Predicate<Constructor<T>> predicate;

    public ConstructorBasedInstanceBuilder(Selector<T> selector, Predicate<Constructor<T>> predicate) {
        super(selector, ctx -> {
            // TODO
            return null;
        });

        this.predicate = predicate;
    }

    @Override
    public Predicate<Constructor<T>> predicate() {
        return this.predicate;
    }

    private Constructor<T> getConstructor(Context<T> context) {
        return Optional.of(Stream
                .of(Util.getConstructors(context.clazz()))
                .filter(this.predicate)
                .collect(Collectors.toList()))
                .map(c -> {
                    if (c.size() == 0) {
                        throw new NewInstanceException(context, "Constructor not found!");
                    } else if (c.size() > 1) {
                        throw new NewInstanceException(context, "More then one constructor found using predicate=" + this.predicate + "!");
                    } else {
                        return c.get(0);
                    }
                })
                .get();
    }
}
