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
package org.genthz.context;

import org.apache.commons.lang3.tuple.Pair;

import java.lang.reflect.Constructor;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * This class contains several methods for context creation.
 *
 * @author mathter
 * @since 3.0.0
 */
public interface ContextFactory {
    /**
     * Method returns {@linkplain InstanceContext} for specified {@code type} and
     * {@code genericArgTypes}.
     *
     * @param type            class of the new object.
     * @param genericArgTypes generic arguments for parametrized class.
     * @param <T>             type of class.
     * @return context.
     * @throws IllegalArgumentException if {@code genericArgTypes} do not matched to {@code type}.
     */
    default <T> InstanceContext<T> single(Class<T> type, Type... genericArgTypes) throws IllegalArgumentException {
        return this.single(null, type, genericArgTypes);
    }

    /**
     * Method like to {@linkplain #single(Class, Type...)} but create new context with
     * specific {@linkplain Bindings}.
     *
     * @param bindings        predefined bindings for new context.
     * @param type            class of the new object.
     * @param genericArgTypes generic arguments for parametrized class.
     * @param <T>             type of class.
     * @return context.
     * @throws IllegalArgumentException if {@code genericArgTypes} do not matched to {@code type}.
     */
    public <T> InstanceContext<T> single(Bindings bindings, Class<T> type, Type... genericArgTypes) throws IllegalArgumentException;

    /**
     * Method returns {@linkplain List} of the contexts corresponds to the parameters of the
     * {@code constructor}.
     *
     * @param up          parent context.
     * @param constructor constructor.
     * @param <T>         type of the class.
     * @return list of the contexts.
     */
    public <T> List<InstanceContext> byConstructor(InstanceContext<T> up, Constructor constructor);

    /**
     * Method returns {@linkplain List} the contexts for elements of collection.
     *
     * @param up    parent context for collection.
     * @param count count of collection elements.
     * @param <T>   type of the collection.
     * @param <E>   type of the collection elements.
     * @return list of the contexts.
     */
    public <T extends Collection, E> List<NodeInstanceContext<E, Integer>> byCollection(InstanceContext<T> up, int count);

    /**
     * Method returns {@linkplain List} of the contexts for elements of java array.
     *
     * @param up    parent context for java array.
     * @param count count of collection elements.
     * @param <T>   type of the java array.
     * @param <E>   type of the array elements.
     * @return list of the contexts.
     */
    public <T, E> List<NodeInstanceContext<E, Integer>> byArray(InstanceContext<T> up, int count);

    /**
     * Method returns {@linkplain Collection} of the contexts for fields of the parent type.
     *
     * @param up  parent context.
     * @param <T> type of parent instance.
     * @return collection of the contexts.
     */
    public <T> Collection<NodeInstanceContext<?, String>> byProperties(InstanceContext<T> up);

    public <K, V, T extends Map<K, V>> Collection<Pair<NodeInstanceContext<K, Integer>, NodeInstanceContext<V, K>>> byMapKey(InstanceContext<T> up, int count);
}
