package org.genthz.dasha.context;

import org.apache.commons.lang3.reflect.TypeUtils;
import org.genthz.context.Bindings;
import org.genthz.context.ContextFactory;
import org.genthz.context.InstanceContext;
import org.genthz.context.NodeInstanceContext;
import org.genthz.reflection.GenericUtil;
import org.genthz.util.StreamUtil;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DashaContextFactory implements ContextFactory {
    private final GenericUtil genericUtil;

    public DashaContextFactory() {
        this(false);
    }

    public DashaContextFactory(boolean strict) {
        this.genericUtil = new GenericUtil(strict);
    }

    @Override
    public <T> InstanceContext<T> single(Bindings bindings, Class<T> type, Type... genericArgTypes) {
        final Type parameterizedType = this.genericUtil.parameterize(type, genericArgTypes);
        final ObjectInstanceAccessor<T> instanceAccessor = new ObjectInstanceAccessor<>();
        return new DashaInstanceContext(
                this,
                bindings,
                instanceAccessor,
                null,
                parameterizedType
        );
    }

    @Override
    public <T> List<InstanceContext> byConstructor(InstanceContext<T> up, Constructor constructor) {
        final List<InstanceContext> result;
        final Type[] parameterTypes = constructor.getGenericParameterTypes();
        final Map<TypeVariable<?>, Type> variableTypeMap = this.genericUtil.getActualTypeArguments(up.type());

        result = new ArrayList<>(constructor.getParameterCount());


        for (int i = 0; i < parameterTypes.length; i++) {
            NodeObjectInstanceAccessor instanceAccessor = new NodeObjectInstanceAccessor(i);
            result.add(new DashaNodeInstanceContext(
                    this,
                    instanceAccessor,
                    up,
                    this.unrollType(variableTypeMap, parameterTypes[i]),
                    instanceAccessor
            ));
        }

        return result;
    }

    @Override
    public <T, E> List<NodeInstanceContext<E, Integer>> byCollection(InstanceContext<T> up, int count) {
        final List<NodeInstanceContext<E, Integer>> result;
        final Type upType = up.type();

        if (Collection.class.isAssignableFrom(TypeUtils.getRawType(upType, Collection.class))) {
            final Map<TypeVariable<?>, Type> variableTypeMap = this.genericUtil.getActualTypeArguments(upType);
            variableTypeMap.putAll(TypeUtils.getTypeArguments(upType, Collection.class));
            result = new ArrayList<>(count);

            for (int i = 0; i < count; i++) {
                CollectionAccessor instanceAccessor = new CollectionAccessor(i, (Collection) up.instance());
                result.add(new DashaNodeInstanceContext(
                        this,
                        instanceAccessor,
                        up,
                        this.unrollType(variableTypeMap, Collection.class.getTypeParameters()[0]),
                        instanceAccessor
                ));
            }
        } else {
            throw new IllegalStateException("Up context class must be instance of " + Collection.class + " !");
        }

        return result;
    }

    @Override
    public <T, E> List<NodeInstanceContext<E, Integer>> byArray(InstanceContext<T> up, int count) {
        final List<NodeInstanceContext<E, Integer>> result;
        final Type upType = up.type();
        final Map<TypeVariable<?>, Type> variableTypeMap = this.genericUtil.getActualTypeArguments(upType);

        if (TypeUtils.isArrayType(upType)) {
            final Type componentType = TypeUtils.getArrayComponentType(upType);
            result = new ArrayList<>(count);

            for (int i = 0; i < count; i++) {
                ArrayAccessor instanceAccessor = new ArrayAccessor(i, (T[]) up.instance());
                result.add(new DashaNodeInstanceContext(
                        this,
                        instanceAccessor,
                        up,
                        this.unrollType(null, componentType),
                        instanceAccessor
                ));
            }
        } else {
            throw new IllegalStateException("Up context class must be instance of array!");
        }

        return result;
    }

    @Override
    public <T> Collection<NodeInstanceContext<?, String>> byProperties(InstanceContext<T> up) {
        final Collection<NodeInstanceContext<?, String>> result;
        final Type upType = up.type();
        final Map<TypeVariable<?>, Type> variableTypeMap = this.genericUtil.getActualTypeArguments(upType);

        result = Optional.of(upType)
                .map(e -> (Class) TypeUtils.getRawType(e, Object.class))
                .map(e -> StreamUtil.of(e, Class::getSuperclass))
                .orElse(Stream.empty())
                .flatMap(e -> Stream.of(e.getDeclaredFields()))
                .filter(e -> Optional.of(e.getModifiers()).map(m -> !Modifier.isFinal(m) && !Modifier.isStatic(m)).get())
                .map(e -> {
                            final FieldInstanceAccessor accessor = new FieldInstanceAccessor(up, e);
                            return (NodeInstanceContext<?, String>) new DashaNodeInstanceContext(
                                    this,
                                    accessor,
                                    up,
                                    this.unrollType(variableTypeMap, e.getGenericType()),
                                    accessor
                            );
                        }
                )
                .collect(Collectors.toList());

        return result;
    }

    private Type unrollType(Map<TypeVariable<?>, Type> variableTypeMap, Type type) {
        return Optional.ofNullable(TypeUtils.unrollVariables(variableTypeMap, type)).orElse(Object.class);
    }
}
