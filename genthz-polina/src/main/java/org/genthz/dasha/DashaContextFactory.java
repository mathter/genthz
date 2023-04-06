package org.genthz.dasha;

import org.genthz.ConstructorChoiceStrategy;
import org.genthz.context.Context;
import org.genthz.context.ContextFactory;
import org.genthz.reflection.GenericUtil;

import java.lang.reflect.Constructor;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;

public class DashaContextFactory implements ContextFactory {
    private final ConstructorChoiceStrategy constructorChoiceStrategy;

    public DashaContextFactory() {
        this(new MinimalArgCountConstructorChoiceStrategy());
    }

    public DashaContextFactory(ConstructorChoiceStrategy constructorChoiceStrategy) {
        this.constructorChoiceStrategy = constructorChoiceStrategy;
    }

    @Override
    public <T> Context<T, Void> context(Class<T> type, Type... genericArgTypes) {
        final Map<TypeVariable, Type> attribution = GenericUtil.attribution(
                Objects.requireNonNull(type),
                genericArgTypes
        );

        ObjectInstanceAccessor<T, Void> instanceAccessor = new ObjectInstanceAccessor<>(null);
        return new AbstractContext(
                this,
                instanceAccessor,
                instanceAccessor,
                null,
                type
        );
    }

    @Override
    public <T> Collection<Context> contexts(Context<T, ?> up) {
        final Collection<Context> result;
        Objects.requireNonNull(up, "up parameter can't be null!");

        switch (Objects.requireNonNull(up, "up parameter can't be null!").stage()) {
            case CRETING:
                result = this.createConstructorArgContexts(up);
                break;

            case CREATED:
                result = this.createContextsForCreated(up);
                break;

            default:
                throw new IllegalStateException();
        }

        return result;
    }

    private <T> Collection<Context> createContextsForCreated(Context<T, ?> up) {
        Class<T> clazz = GenericUtil.rawType(up.type());

        if (Collection.class.isAssignableFrom())
    }

    private <T> Collection<Context> createConstructorArgContexts(Context<T, ?> up) {
        final Collection<Context> result;
        final Constructor<T> constructor = this.constructorChoiceStrategy.constructor(up.type());

        result = new ArrayList<>(constructor.getParameterCount());
        final Class[] parameterTypes = constructor.getParameterTypes();
        for (int i = 0; i < parameterTypes.length; i++) {
            ObjectInstanceAccessor instanceAccessor = new ObjectInstanceAccessor(i);
            result.add(new AbstractContext(
                    this,
                    instanceAccessor,
                    instanceAccessor,
                    up,
                    parameterTypes[i]
            ));
        }

        return result;
    }
}
