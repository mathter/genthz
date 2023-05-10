package org.genthz.function;

import org.genthz.Defaults;
import org.genthz.ObjectFactory;
import org.genthz.context.ContextFactory;
import org.genthz.context.InstanceContext;

public class DefaultCollectionFiller<T> implements Filler<T> {
    @Override
    public void fill(InstanceContext<T> context) {
        final ContextFactory contextFactory = context.contextFactory();
        final ObjectFactory objectFactory = context.objectFactory();
        final Defaults defaults = objectFactory.generationProvider().defaults();

        contextFactory.byCollection(context, defaults.defaultCollectionSize())
                .forEach(e -> objectFactory.process(e));
    }
}
