package org.genthz.simple;

import org.genthz.GenerationProvider;
import org.genthz.ObjectFactory;
import org.genthz.context.Bindings;
import org.genthz.context.ContextFactory;
import org.genthz.context.ObjectContext;
import org.genthz.context.Stage;
import org.genthz.function.Filler;
import org.genthz.function.GenerationException;
import org.genthz.function.InstanceBuilder;

import java.lang.reflect.Type;

public class SimpleObjectFactory implements ObjectFactory {
    private final ContextFactory contextFactory;

    private final GenerationProvider generationProvider;

    public SimpleObjectFactory() {
        this(new SimpleGenerationProvider());
    }

    public SimpleObjectFactory(GenerationProvider generationProvider) {
        this(new SimpleContextFactory(), generationProvider);
    }

    public SimpleObjectFactory(ContextFactory contextFactory, GenerationProvider generationProvider) {
        this.contextFactory = contextFactory;
        this.generationProvider = generationProvider;
    }

    @Override
    public GenerationProvider generationProvider() {
        return this.generationProvider;
    }

    @Override
    public <T> T get(Bindings bindings, Class<T> clazz, Type... genericTypes) {
        final ObjectContext<T, ?> objectContext = this.contextFactory.contexts(bindings, clazz, genericTypes);
        ((Nowable) objectContext).now(() -> SimpleObjectFactory.this);

        return this.build(objectContext).instance();
    }

    private <T> ObjectContext<T, ?> build(ObjectContext<T, ?> context) {
        final Stage origin = context.stage();

        try {
            context.stage(Stage.INSTANCE_CREATING);

            final InstanceBuilder<T> instanceBuilder = this.generationProvider.instanceBuilder(context);
            final T object = instanceBuilder.instance(context);

            context.set(object);
            context.stage(Stage.INSTANCE_CREATED);
        } catch (Throwable t) {
            context.stage(origin);
            throw new GenerationException(t);
        }

        return context;
    }

    private <T> ObjectContext<T, ?> fill(ObjectContext<T, ?> context) {
        final Stage origin = context.stage();

        try {
            context.stage(Stage.FILLING);

            final Filler<T> filler = this.generationProvider.filler(context);
            filler.fill(context);

            context.stage(Stage.COMPLETE);
        } catch (Throwable t) {
            context.stage(origin);
            throw new GenerationException(t);
        }

        return context;
    }
}
