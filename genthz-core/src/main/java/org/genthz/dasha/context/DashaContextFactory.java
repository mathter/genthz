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
import org.genthz.context.Bindings;
import org.genthz.context.ContextFactory;
import org.genthz.context.InstanceContext;
import org.genthz.context.NodeInstanceContext;
import org.genthz.reflection.GenericUtil;
import org.genthz.util.StreamUtil;

import java.lang.reflect.Constructor;
import java.lang.reflect.GenericArrayType;
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
    public <T extends Collection, E> List<NodeInstanceContext<E, Integer>> byCollection(InstanceContext<T> up, int count) {
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
                        this.unrollType(variableTypeMap, componentType),
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
        final Type result;
        if (type instanceof GenericArrayType) {
            result = TypeUtils.genericArrayType(this.unrollType(variableTypeMap, ((GenericArrayType) type).getGenericComponentType()));
        } else {
            result = Optional.ofNullable(TypeUtils.unrollVariables(variableTypeMap, type)).orElse(Object.class);
        }

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
                        this.unrollType(variableTypeMap, Map.class.getTypeParameters()[0]),
                        keyInstanceAccessor
                );
                final MapValueAccessor valueInstanceAccessor = new MapValueAccessor(up.instance(), keyInstanceAccessor, i);
                final NodeInstanceContext<V, K> valueContext = new DashaNodeInstanceContext(
                        this,
                        valueInstanceAccessor,
                        up,
                        this.unrollType(variableTypeMap, Map.class.getTypeParameters()[1]),
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
