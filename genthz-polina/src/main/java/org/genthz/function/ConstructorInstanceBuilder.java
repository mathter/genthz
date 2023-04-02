package org.genthz.function;

import org.genthz.GenerationProvider;
import org.genthz.context.ConstructorContext;
import org.genthz.context.ContextFactory;

import java.lang.reflect.Constructor;

public class ConstructorInstanceBuilder<T> extends DefaultFunction<T> implements InstanceBuilder<T> {
    private final Constructor<T> constructor;

    public ConstructorInstanceBuilder(Constructor<T> constructor) {
        this.constructor = constructor;
    }

    @Override
    public T instance(ConstructorContext<T, ?, ?> context) {
        try {
            final ContextFactory contextFactory = context.contextFactory();
            final GenerationProvider generationProvider = context.now().generationProvider();
            final Constructor<T> constructor = context.constructor();

            return constructor.newInstance(
                    contextFactory.contexts(
                                    context.constructor(this.constructor)
                            ).stream()
                            .map(e -> this.build(e, generationProvider))
                            .map(e -> this.fill(e, generationProvider))
                            .toArray(i -> new ConstructorContext[i])
            );
        } catch (Throwable t) {
            throw new GenerationException(t);
        }
    }
}
