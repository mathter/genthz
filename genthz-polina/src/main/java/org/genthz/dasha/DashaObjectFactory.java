package org.genthz.dasha;

import org.genthz.GenerationProvider;
import org.genthz.ObjectFactory;
import org.genthz.context.Bindings;
import org.genthz.context.ContextFactory;
import org.genthz.context.InstanceContext;
import org.genthz.dasha.context.DashaContextFactory;
import org.genthz.function.Filler;
import org.genthz.function.InstanceBuilder;

import java.lang.reflect.Type;

public class DashaObjectFactory implements ObjectFactory {
    private final ContextFactory contextFactory;

    private final GenerationProvider generationProvider;

    public DashaObjectFactory() {
        this(new DashaContextFactory(), null);
    }

    public DashaObjectFactory(ContextFactory contextFactory, GenerationProvider generationProvider) {
        this.contextFactory = contextFactory;
        this.generationProvider = generationProvider;
    }

    @Override
    public <T> T get(Bindings bindings, Class<T> clazz, Type... genericTypes) {
        final InstanceContext<T> context = this.contextFactory.single(clazz, genericTypes);

        return this.process(context).instance();
    }

    @Override
    public <T> InstanceContext<T> process(InstanceContext<T> context) {
        context.objectFactory(this);

        final InstanceBuilder<T> ib = this.generationProvider.instanceBuilder(context);
        final Filler<T> filler = this.generationProvider.filler(context);

        ib.instance(context);
        filler.fill(context);

        return context;
    }

    @Override
    public GenerationProvider generationProvider() {
        return this.generationProvider;
    }
}
