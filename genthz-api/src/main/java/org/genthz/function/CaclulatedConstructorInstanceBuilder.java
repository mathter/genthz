package org.genthz.function;

import org.genthz.NewInstanceException;
import org.genthz.ObjectFactory;
import org.genthz.context.AbstractConstructorContext;
import org.genthz.context.Context;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ConstructorInstanceBuilder<T> implements InstanceBuilder<T> {

    private final Predicate<Constructor<T>> predicate;

    public ConstructorInstanceBuilder(Predicate<Constructor<T>> predicate) {
        this.predicate = predicate;
    }

    @Override
    public T apply(Context<T> context) {
        return Optional.of(context)
                .map(this::getConstructor)
                .map(e -> build(e, context))
                .get();
    }

    private T build(Constructor<T> constructor, Context<T> context) {
        final T result;

        try {
            result = constructor.newInstance(this.build(constructor, context));
        } catch (Exception e) {
            throw new NewInstanceException(context, "Can't create new incetance!", e);
        }

        return result;
    }

    private Object[] buildParams(Constructor<T> constructor, Context<T> context) {
        final Class<?>[] parameterTypes = constructor.getParameterTypes();
        final List<Object> parameters = new ArrayList<>(parameterTypes.length);

        for (int i = 0; i < parameterTypes.length; i++) {
            final Class<?> parameterType = parameterTypes[i];
            parameters.add(this.buildParameter(context, parameterType, i));
        }

        return parameters.toArray();
    }

    private <P, T> T buildParameter(Context<P> parent, Class<T> clazz, int index) {
        final ObjectFactory objectFactory = parent.objectFactory();
        final Context<T> parameterContext = new AbstractConstructorContext(objectFactory, parent, index, clazz);

        return objectFactory.build(parameterContext).node();
    }

    private Constructor<T> getConstructor(Context<T> context) {
        return Optional.of(Stream
                .of((Constructor<T>[]) context.clazz().getDeclaredConstructors())
                .filter(this.predicate)
                .collect(Collectors.toList()))
                .map(c -> {
                    if (c.size() == 0) {
                        throw new NewInstanceException(context, "Constructor not found!");
                    } else if (c.size() > 1) {
                        throw new NewInstanceException(context, "More then one constructor found using predicate=" + this.predicate + "!");
                    } else {
                        return c.get(0);
                    }
                })
                .get();
    }
}
