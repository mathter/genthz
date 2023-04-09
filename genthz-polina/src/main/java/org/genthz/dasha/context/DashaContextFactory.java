package org.genthz.dasha.context;

import org.genthz.ConstructorChoiceStrategy;
import org.genthz.context.ContextFactory;
import org.genthz.context.InstanceContext;
import org.genthz.reflection.GenericUtil;
import org.genthz.util.StreamUtil;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DashaContextFactory implements ContextFactory {
    private final ConstructorChoiceStrategy constructorChoiceStrategy;

    public DashaContextFactory() {
        this(new MinimalArgCountConstructorChoiceStrategy());
    }

    public DashaContextFactory(ConstructorChoiceStrategy constructorChoiceStrategy) {
        this.constructorChoiceStrategy = constructorChoiceStrategy;
    }

    @Override
    public <T> InstanceContext<T, Void> context(Class<T> type, Type... genericArgTypes) {
        final ObjectInstanceAccessor<T, Void> instanceAccessor = new ObjectInstanceAccessor<>(null);
        return new DashaContext(
                this,
                instanceAccessor,
                instanceAccessor,
                null,
                type
        );
    }

    @Override
    public <U> Collection<InstanceContext> contexts(InstanceContext<U, ?> up) {
        final Collection<InstanceContext> result;

        switch (up.stage()) {
            case NEW:
            case CRETING:
                result = this.createConstructorArgContexts(up);
                break;

            case CREATED:
                result = Optional.of(up.type())
                        .map(GenericUtil::rawType)
                        .map(e -> StreamUtil.of(e, Class::getSuperclass))
                        .orElse(Stream.empty())
                        .flatMap(e -> Stream.of(e.getDeclaredFields()))
                        .filter(e -> Optional.of(e.getModifiers()).map(m -> !Modifier.isFinal(m) && !Modifier.isStatic(m)).get())
                        .map(e -> {
                                    final FieldInstanceAccessor accessor = new FieldInstanceAccessor(up, e);
                                    return new DashaContext(
                                            this,
                                            accessor,
                                            accessor,
                                            up,
                                            GenericUtil.rawType(e.getDeclaringClass())
                                    );
                                }
                        )
                        .collect(Collectors.toList());
                break;

            default:
                throw new IllegalStateException(
                        String.format(
                                "Only NEW, CREATING and CREATED stages are supported! Currenct stage is '%s'",
                                up.stage())
                );
        }

        return result;
    }

    @Override
    public <U, T, N, L, LN> InstanceContext<T, N> context(InstanceContext<U, ?> up,
                                                          int order,
                                                          Class<T> type,
                                                          InstanceContext<L, LN> left) {
        final ObjectInstanceAccessor accessor;
        final Class<U> upClazz = GenericUtil.rawType(up.type());

        if (left == null) {
            if (Collection.class.isAssignableFrom(upClazz)) {
                accessor = new CollectionAccessor(order, (Collection) up.instance());
            } else if (Map.class.isAssignableFrom(upClazz)) {
                accessor = new MapAccessor(order, (Map) up.instance());
            } else {
                throw new IllegalStateException(
                        String.format("Can't create context for class: %s. <? extends java.util.Collection> and <? extends java.util.Map> are supported only!",
                                upClazz)
                );
            }
        } else {
            if (Map.class.isAssignableFrom(upClazz)) {
                accessor = ((MapAccessor) ((DashaContext) left).getInstanceAccessor()).valueAccessor();
            } else {
                throw new IllegalStateException(
                        String.format("Can't create value context for class: %s. <? extends java.util.Map> are supported only!\",\n")
                );
            }
        }

        return new DashaContext<>(
                this,
                accessor,
                accessor,
                up,
                left,
                GenericUtil.rawType(type)
        );
    }

    private <T> Collection<InstanceContext> createFieldContexts(InstanceContext<T, ?> up) {
        return Optional.of(up.type())
                .map(GenericUtil::rawType)
                .map(e -> StreamUtil.of(e, Class::getSuperclass))
                .orElse(Stream.empty())
                .flatMap(e -> Stream.of(e.getDeclaredFields()))
                .filter(e -> Optional.of(e.getModifiers()).map(m -> !Modifier.isFinal(m) && !Modifier.isStatic(m)).get())
                .map(e -> {
                            final FieldInstanceAccessor accessor = new FieldInstanceAccessor(up, e);
                            return new DashaContext(
                                    this,
                                    accessor,
                                    accessor,
                                    up,
                                    GenericUtil.rawType(e.getDeclaringClass())
                            );
                        }
                )
                .collect(Collectors.toList());
    }

    private <T> Collection<InstanceContext> createConstructorArgContexts(InstanceContext<T, ?> up) {
        final Collection<InstanceContext> result;
        final Constructor<T> constructor = this.constructorChoiceStrategy.constructor(up.type());

        result = new ArrayList<>(constructor.getParameterCount());
        final Class[] parameterTypes = constructor.getParameterTypes();
        for (int i = 0; i < parameterTypes.length; i++) {
            ObjectInstanceAccessor instanceAccessor = new ObjectInstanceAccessor(i);
            result.add(new DashaContext(
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
