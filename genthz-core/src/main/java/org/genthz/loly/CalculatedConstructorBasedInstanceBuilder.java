package org.genthz.loly;

import org.genthz.Context;
import org.genthz.Description;
import org.genthz.Filler;
import org.genthz.InstanceBuilder;
import org.genthz.NewInstanceException;
import org.genthz.Util;
import org.genthz.loly.context.ConstructorContext;
import org.genthz.loly.context.ValueContext;
import org.genthz.loly.reflect.Accessor;

import java.lang.reflect.Constructor;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class CalculatedConstructorBasedInstanceBuilder<T> implements InstanceBuilder<T> {

    private final Predicate<Constructor<T>> predicate;

    private final Description description;

    public CalculatedConstructorBasedInstanceBuilder(Predicate<Constructor<T>> predicate, Description description) {
        this.predicate = predicate;
        this.description = description;
    }

    @Override
    public T apply(Context<T> context) {
        final T object;
        final Constructor<T> constructor = this.getConstructor(context);
        final Object[] params = Stream
                .of(constructor.getParameterTypes())
                .map(c -> new ConstructorContext(c, context.objectFactory(), (ValueContext<?>) context))
                .map(c -> {
                    final InstanceBuilder instanceBuilder = c.objectFactory().instanceBuilder(c);
                    final Filler filler = c.objectFactory().filler(c);

                    Optional
                            .ofNullable(instanceBuilder.apply(c))
                            .map(e -> {
                                ((Accessor) c).setInstance(e);
                                ((Accessor) c).setFilled(filler.apply(c, e));
                                return c;
                            })
                            .get();

                    return c;
                })
                .map(c -> c.get())
                .collect(Collectors.toList())
                .toArray();

        try {
            object = Util.newInstance(constructor, params);
        } catch (Exception e) {
            throw new NewInstanceException(context, e);
        }

        return object;
    }

    private Constructor<T> getConstructor(Context<T> context) {
        return Optional.of(Stream
                .of(Util.getConstructors(context.clazz()))
                .filter(this.predicate)
                .collect(Collectors.toList()))
                .map(c -> {
                    if (c.size() == 0) {
                        throw new NewInstanceException(context, "Constructor not found!");
                    } else if (c.size() > 1) {
                        throw new NewInstanceException(context, "More then one constructor found using predicate="+this.predicate+"!");
                    } else {
                        return c.get(0);
                    }
                })
                .get();
    }
}
