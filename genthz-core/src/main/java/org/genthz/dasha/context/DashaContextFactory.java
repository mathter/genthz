package org.genthz.dasha.context;

import org.apache.commons.lang3.reflect.TypeUtils;
import org.genthz.context.Bindings;
import org.genthz.context.ContextFactory;
import org.genthz.context.InstanceContext;
import org.genthz.context.NodeInstanceContext;
import org.genthz.util.StreamUtil;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DashaContextFactory implements ContextFactory {
    private static final Type[] EMPTY_GENERIC_ARG_TYPE = new Type[0];

    @Override
    public <T> InstanceContext<T> single(Bindings bindings, Class<T> type, Type... genericArgTypes) {
        final TypeVariable<?>[] typeParameters = type.getTypeParameters();

        if ((genericArgTypes == null && typeParameters.length == 0)
                || (genericArgTypes != null && typeParameters.length == genericArgTypes.length)) {
            final ParameterizedType parameterizedType = TypeUtils.parameterize(type, genericArgTypes != null ? genericArgTypes : EMPTY_GENERIC_ARG_TYPE);
            final ObjectInstanceAccessor<T> instanceAccessor = new ObjectInstanceAccessor<>();
            return new DashaInstanceContext(
                    this,
                    bindings,
                    instanceAccessor,
                    null,
                    parameterizedType
            );
        } else {
            throw new IllegalArgumentException(
                    String.format(
                            "Invalid count of parameters specified for class %s: expected %d, got %s ",
                            type,
                            typeParameters.length,
                            genericArgTypes != null ? genericArgTypes.length : null
                    )
            );
        }
    }

    @Override
    public <T> List<InstanceContext> byConstructor(InstanceContext<T> up, Constructor constructor) {
        final List<InstanceContext> result;
        final Type[] parameterTypes = constructor.getGenericParameterTypes();
        final Map<TypeVariable<?>, Type> variableTypeMap = this.getTypeArguments(up.type());

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
            final Map<TypeVariable<?>, Type> variableTypeMap = TypeUtils.getTypeArguments(upType, Collection.class);
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
        return null;
    }

    @Override
    public <T> Collection<NodeInstanceContext<?, String>> byProperties(InstanceContext<T> up) {
        final Collection<NodeInstanceContext<?, String>> result;
        final Type upType = up.type();
        final Map<TypeVariable<?>, Type> variableTypeMap = this.getTypeArguments(upType);

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

    private Map<TypeVariable<?>, Type> getTypeArguments(Type type) {
        final Map<TypeVariable<?>, Type> result;

        if (type instanceof Class) {
            result = TypeUtils.getTypeArguments(type, null);
        } else if (type instanceof ParameterizedType) {
            result = TypeUtils.getTypeArguments((ParameterizedType) type);
        } else {
            throw new IllegalArgumentException(
                    String.format("Argument type must be %s or %s! Currently %s", Class.class, ParameterizedType.class, type)
            );
        }

        return result;
    }
}
