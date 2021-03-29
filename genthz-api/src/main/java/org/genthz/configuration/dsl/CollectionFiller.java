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

import org.genthz.Filler;

/**
 * Defines collection filler.
 *
 * @param <T> type of the collection.
 * @param <C> type of the collection components.
 * @author <a href="mailto:mathter@mail.ru">mathter</a>
 * @version 1.0.0
 * @since 1.0.0
 */
public interface CollectionFiller<T, C> extends Selectable {

    /**
     * Method returns type of the collection.
     *
     * @return class of the collection.
     */
    public Class<T> collectionClass();

    /**
     * Method returns type of the collection components.
     *
     * @return class of the collection components.
     */
    public Class<C> componentClass();

    /**
     * Method returns count of the items should be constructed and added to the collection.
     *
     * @return count of collection elements.
     */
    public int count();

    /**
     * This method defines custom collection filler function.
     * This function will be called after default collection filling is completed.
     *
     * @param function filler function.
     * @return itself.
     */
    public CollectionFiller<T, C> custom(Filler<T> function);

    /**
     * Method returns custom collection filler function.
     *
     * @return custom collection filler function.
     */
    public Filler<T> custom();

    /**
     * This method defines custom collection component filler function.
     * This function will be called after default filling of collection component is completed.
     *
     * @param function custom collection component function.
     * @return itself.
     */
    public CollectionFiller<T, C> componentCustom(Filler<C> function);

    /**
     * Method returns custom collection component filler function.
     *
     * @return custom collection component filler function.
     */
    public Filler<C> componentCustom();
}
