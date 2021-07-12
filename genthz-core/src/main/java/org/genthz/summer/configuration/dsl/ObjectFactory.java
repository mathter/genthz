package org.genthz.summer;

import org.genthz.Configuration;
import org.genthz.context.Context;
import org.genthz.function.Filler;
import org.genthz.function.InstanceBuilder;
import org.genthz.RootContext;
import org.genthz.context.Spec;

import java.util.Optional;

class ObjectFactory implements org.genthz.ObjectFactory {
    private final Configuration configuration;

    public ObjectFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public <T> RootContext<T> build(Context<T> initContext) {
//        final ObjectContext<T> context = new ObjectContext<T>(this, initContext.bindings(), initContext.clazz());
//        final InstanceBuilder<T> instanceBuilder = this.instanceBuilder(context);
//        final Filler<T> filler = this.filler(context);
//        final T object = instanceBuilder.apply(context);
//
//        context.setInstance(object);
//        context.setFilled(filler.apply(context, object));
//
//        return context;

        return null;
    }

    @Override
    public <T> InstanceBuilder<T> instanceBuilder(Context<T> context) {
        return Optional.ofNullable(this.configuration.instanceBuilder(context))
                .orElse(this.buildDefaultInstanceBuilder(context));
    }

    @Override
    public <T> Filler<T> filler(Context<T> context) {
        return Optional.ofNullable(this.configuration.filler(context))
                .orElse(this.buildDefaultFiller(context));
    }

    private <T> InstanceBuilder<T> buildDefaultInstanceBuilder(Context<T> context) {
        return null;
    }

    private <T> Filler<T> buildDefaultFiller(Context<T> context) {
        return null;
    }
}
