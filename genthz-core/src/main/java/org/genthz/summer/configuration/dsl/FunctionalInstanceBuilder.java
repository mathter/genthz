package org.genthz.function;

import org.genthz.context.Context;
import org.genthz.function.InstanceBuilder;
import org.genthz.configuration.dsl.Selector;

import java.util.function.Function;

class FunctionalInstanceBuilder<T>
        extends Selectable<T>
        implements org.genthz.configuration.dsl.FunctionalInstanceBuilder<T>, InstanceBuilder<T> {
    private final Function<Context<T>, T> function;

    public FunctionalInstanceBuilder(Selector<T> selector, Function<Context<T>, T> function) {
        super(selector);
        this.function = function;
    }

    @Override
    public Function<Context<T>, T> function() {
        return this.function;
    }

    @Override
    public T apply(Context<T> context) {
        return this.function.apply(context);
    }
}
