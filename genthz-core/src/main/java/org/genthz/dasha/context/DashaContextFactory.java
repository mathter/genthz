/*
 * Generated - testing becomes easier
 *
 * Copyright (C) 2023 mathter@mail.ru
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.genthz.dasha.context;

import org.apache.commons.lang3.reflect.TypeUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.genthz.context.AccessorResolver;
import org.genthz.context.Bindings;
import org.genthz.context.ContextFactory;
import org.genthz.context.InstanceContext;
import org.genthz.context.NodeInstanceContext;
import org.genthz.dasha.DashaObjectFactory;
import org.genthz.reflection.GenericUtil;
import org.genthz.reflection.Util;
import org.genthz.util.StreamUtil;

import java.lang.reflect.Constructor;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class DashaContextFactory implements ContextFactory {
    private final GenericUtil genericUtil;

    private final AccessorResolver accessorResolver;

    public DashaContextFactory() {
        this(false);
    }

    public DashaContextFactory(boolean strict) {
        this(new DashaAccessorResolver(), strict);
    }

    public DashaContextFactory(AccessorResolver accessorResolver) {
        this(accessorResolver, false);
    }

    public DashaContextFactory(AccessorResolver accessorResolver, boolean strict) {
        this.accessorResolver = Objects.requireNonNull(accessorResolver, "accessorResolver parameter can't be null!");
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
    public <T> List<NodeInstanceContext<?, Integer>> byConstructor(InstanceContext<T> up, Constructor constructor) {
        final List<NodeInstanceContext<?, Integer>> result;
        final Type[] parameterTypes = constructor.getGenericParameterTypes();
        final Map<TypeVariable<?>, Type> variableTypeMap = this.genericUtil.getActualTypeArguments(up.type());

        result = new ArrayList<>(constructor.getParameterCount());


        for (int i = 0; i < parameterTypes.length; i++) {
            NodeObjectInstanceAccessor instanceAccessor = new NodeObjectInstanceAccessor(i);
            result.add(new DashaNodeInstanceContext(
                    this,
                    instanceAccessor,
                    up,
                    Util.unrollType(variableTypeMap, parameterTypes[i]),
                    instanceAccessor,
                    true
            ));
        }

        return result;
    }

    @Override
    public <T extends Collection, E> List<NodeInstanceContext<E, Integer>> byCollection(InstanceContext<T> up, int count) {
        final List<NodeInstanceContext<E, Integer>> result;
        final Type upType = up.type();

        if (Collection.class.isAssignableFrom(TypeUtils.getRawType(upType, Collection.class))) {
            final Map<TypeVariable<?>, Type> variableTypeMap = this.genericUtil.getActualTypeArguments(upType);
            variableTypeMap.putAll(TypeUtils.getTypeArguments(upType, Collection.class));
            result = new ArrayList<>(count);

            for (int i = 0; i < count; i++) {
                CollectionAccessor instanceAccessor = new CollectionAccessor(i, () -> up.get());
                result.add(new DashaNodeInstanceContext(
                        this,
                        instanceAccessor,
                        up,
                        Util.unrollType(variableTypeMap, Collection.class.getTypeParameters()[0]),
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
                ArrayAccessor instanceAccessor = new ArrayAccessor(i, () -> up.instance());
                result.add(new DashaNodeInstanceContext(
                        this,
                        instanceAccessor,
                        up,
                        Util.unrollType(variableTypeMap, componentType),
                        instanceAccessor
                ));
            }
        } else {
            throw new IllegalStateException("Up context class must be instance of array!");
        }

        return result;
    }

    @Override
    public <T extends Stream, E> Stream<NodeInstanceContext<E, Integer>> byStream(InstanceContext<T> up, Stream<Integer> indexStream) {
        final Type upType = up.type();
        final Map<TypeVariable<?>, Type> variableTypeMap = this.genericUtil.getActualTypeArguments(upType);
        variableTypeMap.putAll(TypeUtils.getTypeArguments(upType, Stream.class));

        return indexStream
                .map(index -> {
                    final NodeObjectInstanceAccessor accessor = new NodeObjectInstanceAccessor(index);

                    return new DashaNodeInstanceContext(
                            this,
                            accessor,
                            up,
                            Util.unrollType(variableTypeMap, Stream.class.getTypeParameters()[0]),
                            accessor
                    );
                });
    }

    @Override
    public <T> Collection<NodeInstanceContext<?, String>> byProperties(InstanceContext<T> up) {
        return this.byProperties(up, this.accessorResolver);
    }

    @Override
    public <T> Collection<NodeInstanceContext<?, String>> byProperties(InstanceContext<T> up, AccessorResolver accessorResolver) {
        final Collection<NodeInstanceContext<?, String>> result = Objects.requireNonNull(accessorResolver, "accessorResolver parameter can't be null!")
                .resolve(up)
                .stream()
                .map(e -> (NodeInstanceContext<?, String>) new DashaNodeInstanceContext(
                        this,
                        e,
                        up,
                        e.type(),
                        e
                ))
                .collect(Collectors.toList());

        return result;
    }

    @Override
    public <K, V, T extends Map<K, V>> Collection<Pair<NodeInstanceContext<K, Integer>, NodeInstanceContext<V, K>>> byMapKey(InstanceContext<T> up, int count) {
        final Collection<Pair<NodeInstanceContext<K, Integer>, NodeInstanceContext<V, K>>> result;
        final Type upType = up.type();

        if (Map.class.isAssignableFrom(TypeUtils.getRawType(upType, Collection.class))) {
            final Map<TypeVariable<?>, Type> variableTypeMap = this.genericUtil.getActualTypeArguments(upType);
            variableTypeMap.putAll(TypeUtils.getTypeArguments(upType, Map.class));
            result = new ArrayList<>(count);

            for (int i = 0; i < count; i++) {
                final MapKeyAccessor keyInstanceAccessor = new MapKeyAccessor(i);
                final NodeInstanceContext<K, Integer> keyContext = new DashaNodeInstanceContext(
                        this,
                        keyInstanceAccessor,
                        up,
                        Util.unrollType(variableTypeMap, Map.class.getTypeParameters()[0]),
                        keyInstanceAccessor
                );
                final MapValueAccessor valueInstanceAccessor = new MapValueAccessor(up.instance(), keyInstanceAccessor, i);
                final NodeInstanceContext<V, K> valueContext = new DashaNodeInstanceContext(
                        this,
                        valueInstanceAccessor,
                        up,
                        Util.unrollType(variableTypeMap, Map.class.getTypeParameters()[1]),
                        valueInstanceAccessor
                );

                result.add(Pair.of(keyContext, valueContext));
            }
        } else {
            throw new IllegalStateException("Up context class must be instance of " + Map.class + " !");
        }

        return result;
    }
}
