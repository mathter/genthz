package org.genthz.function;

import org.genthz.GenerationProvider;
import org.genthz.context.ConstructorContext;
import org.genthz.context.Stage;

class DefaultFunction<T> {
    protected ConstructorContext build(ConstructorContext context, GenerationProvider generationProvider) {
        final Stage origin = context.stage();

        if (Stage.NEW == origin) {
            try {
                context.stage(Stage.INSTANCE_CREATING);

                final InstanceBuilder<T> instanceBuilder = generationProvider.instanceBuilder(context);
                final Object object = instanceBuilder.instance(context);

                context.set(object);
                context.stage(Stage.INSTANCE_CREATED);
            } catch (Throwable t) {
                context.stage(origin);
                throw new GenerationException(t);
            }
        } else {
            throw new IllegalStateException("Context must have stage=NEW");
        }

        return context;
    }

    protected ConstructorContext fill(ConstructorContext context, GenerationProvider generationProvider) {
        final Stage origin = context.stage();

        try {
            context.stage(Stage.FILLING);

            final Filler<T> filler = generationProvider.filler(context);
            filler.fill(context);

            context.stage(Stage.COMPLETE);
        } catch (Throwable t) {
            context.stage(origin);
            throw new GenerationException(t);
        }

        return context;
    }
}
