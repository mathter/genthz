package org.genthz.dasha;

import org.genthz.GenerationProvider;
import org.genthz.ObjectFactory;
import org.genthz.context.Bindings;
import org.genthz.context.ContextFactory;
import org.genthz.context.InstanceContext;
import org.genthz.context.Stage;
import org.genthz.dasha.context.DashaContextFactory;
import org.genthz.dasha.dsl.DashaDsl;
import org.genthz.function.Filler;
import org.genthz.function.InstanceBuilder;

import java.lang.reflect.Type;

public class DashaObjectFactory implements ObjectFactory {
    private final ContextFactory contextFactory;

    private final GenerationProvider generationProvider;

    public DashaObjectFactory() {
        this(null, null);
    }

    public DashaObjectFactory(GenerationProvider generationProvider) {
        this(null, generationProvider);
    }

    public DashaObjectFactory(ContextFactory contextFactory, GenerationProvider generationProvider) {
        this.contextFactory = contextFactory != null ? contextFactory : new DashaContextFactory();
        this.generationProvider = generationProvider != null ? generationProvider : new DashaDsl().def().build();
    }

    @Override
    public <T> T get(Bindings bindings, Class<T> clazz, Type... genericTypes) {
        final InstanceContext<T> context = this.contextFactory.single(clazz, genericTypes);

        return this.process(context).instance();
    }

    @Override
    public <T> InstanceContext<T> process(InstanceContext<T> context) {
        switch (context.stage()) {
            case NEW:
                this.processNew(context);
                break;

            case COMPLETE:
                break;

            default:
                throw new IllegalStateException(
                        String.format("Context %s stage error!", context)
                );
        }

        return context;
    }

    private <T> void processNew(InstanceContext<T> context) {
        context.objectFactory(this);

        final InstanceBuilder<T> ib = this.generationProvider.instanceBuilder(context);
        final Filler<T> filler = this.generationProvider.filler(context);

        try {
            context.stage(Stage.CREATING);
            ib.instance(context);
            context.stage(Stage.CREATED);
        } catch (Throwable t) {
            context.stage(Stage.NEW);
            throw new IllegalStateException(
                    String.format("Can't create object instance for context %s", context),
                    t
            );
        }

        try {
            context.stage(Stage.FILLING);
            filler.fill(context);
            context.stage(Stage.COMPLETE);
        } catch (Throwable t) {
            context.stage(Stage.CREATED);
            throw new IllegalStateException(
                    String.format("Can't create object instance for context %s", context),
                    t
            );
        }
    }

    @Override
    public GenerationProvider generationProvider() {
        return this.generationProvider;
    }
}
