package org.genthz.simple;

import org.genthz.ConstructorChoiceStrategy;
import org.genthz.context.ContextException;
import org.genthz.context.ContextFactory;
import org.genthz.context.Bindings;
import org.genthz.context.ConstructorContext;
import org.genthz.context.FieldContext;
import org.genthz.context.ObjectContext;
import org.genthz.context.Stage;
import org.genthz.reflection.GenericUtil;
import org.genthz.util.StreamUtil;

import java.lang.reflect.Constructor;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SimpleContextFactory implements ContextFactory {
    private final ConstructorChoiceStrategy constructorChoiceStrategy;

    public SimpleContextFactory() {
        this(new MinimalArgsCountConstructorChoiceStrategy());
    }

    public SimpleContextFactory(ConstructorChoiceStrategy constructorChoiceStrategy) {
        this.constructorChoiceStrategy = constructorChoiceStrategy;
    }

    @Override
    public <T> ObjectContext<T, ?> contexts(Bindings bindings, Class<T> clazz, Type... genericTypes) {
        final Constructor<T> constructor = clazz.isInterface() ? null : this.constructorChoiceStrategy.constructor(clazz);
        final ObjectContext context = new org.genthz.simple.ObjectContext(
                this,
                bindings != null ? bindings : new org.genthz.simple.Bindings(),
                null,
                GenericUtil.resolve(clazz, GenericUtil.attribution(clazz, genericTypes)),
                GenericUtil.attribution(clazz, genericTypes),
                constructor
        );

        return context;
    }

    @Override
    public <T> Collection<ConstructorContext<?, ?, ?>> contexts(ConstructorContext<T, ?, ?> context) {
        final Collection<ConstructorContext<?, ?, ?>> result;
        final Constructor<T> constructor = context.constructor();

        if (Stage.INSTANCE_CREATED == context.stage()) {
            final T instance = context.instance();

            if (instance != null) {
                result = this.fieldContexts((Class<T>) instance.getClass(), context).collect(Collectors.toList());
            } else {
                result = Collections.emptyList();
            }
        } else if (constructor != null && constructor.getParameterCount() == 0) {
            result = this.fieldContexts(constructor.getDeclaringClass(), context).collect(Collectors.toList());
        } else if (Stage.INSTANCE_CREATING == context.stage()) {
            result = this.constructorArgContexts(context).collect(Collectors.toList());
        } else {
            throw new ContextException(String.format("Can't process context %s. Only stage=INSTANCE_CREATING|INSTANCE_CREATED can be processed!", context));
        }

        return result;
    }

    private <T> Stream<ConstructorArgContext<?, T, ?>> constructorArgContexts(ConstructorContext<T, ?, ?>
                                                                                      parent) {
        final Collection<ConstructorArgContext<?, T, ?>> contexts = new ArrayList<>();
        final Type[] argumentTypes = parent.constructor().getGenericParameterTypes();

        for (int i = 0; i < argumentTypes.length; i++) {
            final Type argumentClass = GenericUtil.resolve(argumentTypes[i], parent.genericAtribution());

            contexts.add(
                    new ConstructorArgContext(
                            this,
                            parent,
                            argumentClass,
                            parent.genericAtribution(),
                            this.constructorChoiceStrategy.constructor(argumentClass),
                            i
                    )
            );
        }

        return contexts.stream();
    }

    private <T> Stream<FieldContext<?, T, ?>> fieldContexts(Class<T> clazz, ConstructorContext<T, ?, ?>
            parent) {
        return StreamUtil.of((Class) clazz, Class::getSuperclass)
                .flatMap(e -> Stream.of(e.getDeclaredFields()))
                .map(e -> {
                            final Type fieldType = GenericUtil.resolve(e.getGenericType(), parent.genericAtribution());
                            return new org.genthz.simple.FieldContext(
                                    this,
                                    parent,
                                    fieldType,
                                    parent.genericAtribution(),
                                    this.constructorChoiceStrategy.constructor(fieldType),
                                    e);
                        }
                );
    }
}
