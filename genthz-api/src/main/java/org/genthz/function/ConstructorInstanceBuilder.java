package org.genthz.context;

import org.genthz.NewInstanceException;
import org.genthz.ObjectFactory;
import org.genthz.function.InstanceBuilder;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ConstructorInstanceBuilder<T> implements InstanceBuilder<T> {
    private final Constructor<T> constructor;

    public ConstructorInstanceBuilder(Constructor<T> constructor) {
        this.constructor = constructor;
    }

    @Override
    public T apply(Context<T> context) {
        return Optional.of(context)
                .map(e -> build(this.constructor, e))
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
}
