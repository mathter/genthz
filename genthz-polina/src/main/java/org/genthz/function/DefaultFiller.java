package org.genthz.function;

import org.genthz.GenerationProvider;
import org.genthz.context.ConstructorContext;
import org.genthz.context.ContextFactory;

public class DefaultFiller<T> extends DefaultFunction<T> implements Filler<T> {
    @Override
    public void fill(ConstructorContext<T, ?, ?> context) {
        final ContextFactory contextFactory = context.contextFactory();
        final GenerationProvider generationProvider = context.now().generationProvider();

        try {
            contextFactory.contexts(context)
                    .stream()
                    .map(e -> this.build(e, generationProvider))
                    .forEach(e->this.fill(e,generationProvider));
        } catch (Throwable t) {
            throw new GenerationException(t);
        }
    }
}
