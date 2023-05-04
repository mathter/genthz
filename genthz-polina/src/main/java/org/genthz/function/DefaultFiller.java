package org.genthz.function;

import org.genthz.ObjectFactory;
import org.genthz.context.ContextFactory;
import org.genthz.context.InstanceContext;

public class DefaultFiller<T> implements Filler<T> {
    @Override
    public void fill(InstanceContext<T> context) {
        final ContextFactory contextFactory = context.contextFactory();
        final ObjectFactory objectFactory = context.objectFactory();

        contextFactory.byProperties(context).stream()
                .forEach(e -> objectFactory.process(e));
    }
}
