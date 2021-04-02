package org.genthz.loly;

import org.genthz.Context;
import org.genthz.Description;
import org.genthz.Filler;
import org.genthz.InstanceBuilder;
import org.genthz.configuration.dsl.ConstructorNotFoundException;
import org.genthz.configuration.dsl.MoreThenOneConstructorFoundException;
import org.genthz.loly.context.ConstructorContext;
import org.genthz.loly.context.ValueContext;
import org.genthz.loly.reflect.Accessor;

import java.lang.reflect.Constructor;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class CalculatedConstructorBasedInstanceBuilder<T> implements InstanceBuilder<T> {

    private final Predicate<Constructor<?>> predicate;

    private final Description description;

    public CalculatedConstructorBasedInstanceBuilder(Predicate<Constructor<?>> predicate, Description description) {
        this.predicate = predicate;
        this.description = description;
    }

    @Override
    public T apply(Context<?> context) {
        final T object;
        final Object[] params = Stream
                .of(this.getConstructor((Context<T>) context).getParameterTypes())
                .map(c -> new ConstructorContext(c, context.objectFactory(), (ValueContext<?>) context))
                .map(c -> {
                    final InstanceBuilder<T> instanceBuilder = c.objectFactory().instanceBuilder(c);
                    final Filler<T> filler = c.objectFactory().filler(c);

                    Optional
                            .ofNullable(instanceBuilder.apply(c))
                            .map(e -> {
                                ((Accessor<T>) c).setInstance(e);
                                ((Accessor<T>) c).setFilled((T) filler.apply(c, e));
                                return c;
                            })
                            .get();

                    return c;
                })
                .map(c -> c.get())
                .collect(Collectors.toList())
                .toArray();

        try {
            object = this.getConstructor((Context<T>) context).newInstance(params);
        } catch (Exception e) {
            // TODO Change exception type.
            throw new RuntimeException("Can't constuct object for context: " + context, e);
        }

        return object;
    }

    private Constructor<T> getConstructor(Context<T> context) {
        return (Constructor<T>) Optional.of(Stream
                .of(context.clazz().getConstructors())
                .filter(this.predicate)
                .collect(Collectors.toList()))
                .map(c -> {
                    if (c.size() == 0) {
                        throw new ConstructorNotFoundException(this.description);
                    } else if (c.size() > 1) {
                        throw new MoreThenOneConstructorFoundException(this.description);
                    } else {
                        return c.get(0);
                    }
                })
                .get();
    }
}