/*
 * Generated - testing becomes easier
 *
 * Copyright (C) 2020 mathter@mail.ru
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
package org.genthz.configuration.dsl;

/**
 * Interface defines producer of the default collection fillers.
 *
 * @author <a href="mailto:mathter@mail.ru">mathter</a>
 * @version 1.0.0
 * @since 1.0.0
 */
public interface CollectionFillered {

    /**
     * Method returns collection filler for specified parameters of the collection.
     *
     * @param componentClazz component class
     * @param count          count of components in the collection.
     * @param <T>            type of the collection.
     * @param <C>            type of the collection component.
     * @return defulat collection filler.
     */
    default public <T, C> CollectionFiller<T, C> collectionFiller(
            Class<C> componentClazz,
            int count
    ) {
        return this.collectionFiller(null, componentClazz, count);
    }

    /**
     * Method returns collection filler for specified parameters of the collection.
     *
     * @param collectionClazz collection class.
     * @param componentClazz  component class
     * @param count           count of components in the collection.
     * @param <T>             type of the collection.
     * @param <C>             type of the collection component.
     * @return collection filler.
     */
    public <T, C> CollectionFiller<T, C> collectionFiller(
            Class<T> collectionClazz, Class<C> componentClazz,
            int count
    );
}
