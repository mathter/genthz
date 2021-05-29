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

import java.util.Collection;
import java.util.Deque;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.function.Supplier;

/**
 * Class defines same method for common logic building and filling objects.
 *
 * @author <a href="mailto:mathter@mail.ru">mathter</a>
 * @version 1.0.0
 * @since 1.0.0
 */
public interface Specification {

    /**
     * Method defines max deep of hierarchy to be constructed.
     *
     * @return {@linkplain Supplier} for calculating maxumum object nesting.
     */
    public Supplier<Long> maxGenerationDeep();

    /**
     * Method returns default class of instance for object fields class of {@linkplain Collection}.
     *
     * @return object class.
     */
    public Supplier<Class<? extends Collection>> defaultCollectionClass();

    /**
     * Method returns default class of instance for object fields class of {@linkplain List}.
     *
     * @return object class.
     */
    public Supplier<Class<? extends List>> defaultListClass();

    /**
     * Method returns default class of instance for object fields class of {@linkplain Set}.
     *
     * @return object class.
     */
    public Supplier<Class<? extends Set>> defaultSetClass();

    /**
     * Method returns default class of instance for object fields class of {@linkplain Queue}.
     *
     * @return object class.
     */
    public Supplier<Class<? extends Queue>> defaultQueueClass();

    /**
     * Method returns default class of instance for object fields class of {@linkplain Deque}.
     *
     * @return object class.
     */
    public Supplier<Class<? extends Deque>> defaultDequeClass();

    /**
     * Method returns default element count of the collection.
     *
     * @return element count of the collection.
     */
    public Supplier<Integer> defaultCollectionSize();

    /**
     * Method returns default type of the collection element.
     *
     * @return default type of the collection element
     */
    public Supplier<Class<?>> defaultCollectionItemClass();
}
