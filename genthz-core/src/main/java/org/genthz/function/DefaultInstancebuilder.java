package org.genthz.function;

import org.genthz.ConstructorChoiceStrategy;
import org.genthz.ObjectFactory;
import org.genthz.context.ContextFactory;
import org.genthz.context.InstanceContext;
import org.genthz.dasha.context.MinimalArgCountConstructorChoiceStrategy;

import java.lang.reflect.Constructor;

public class DefaultInstancebuilder<T> implements InstanceBuilder<T> {
    private final ConstructorChoiceStrategy constructorChoiceStrategy;

    public DefaultInstancebuilder() {
        this(new MinimalArgCountConstructorChoiceStrategy());
    }

    public DefaultInstancebuilder(ConstructorChoiceStrategy constructorChoiceStrategy) {
        this.constructorChoiceStrategy = constructorChoiceStrategy;
    }

    @Override
    public void instance(InstanceContext<T> context) {
        final ContextFactory contextFactory = context.contextFactory();
        final ObjectFactory objectFactory = context.objectFactory();
        final Constructor constructor = this.constructorChoiceStrategy.constructor(context.type());
        final T instance;

        try {
            instance = (T) constructor.newInstance(contextFactory.byConstructor(context, constructor)
                    .stream()
                    .map(e -> objectFactory.process(e).instance())
                    .toArray(i -> new Object[i]));
        } catch (Throwable t) {
            throw new RuntimeException(t);
        }

        context.set(instance);
    }
}
