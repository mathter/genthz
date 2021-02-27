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

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Supplier;

public class SpecificationAdapter implements Specification {
    static final Supplier<Long> DEFAULT_MAX_GENERATION_DEEP = () -> 100L;

    static final Supplier<Class<? extends Collection>> DEFAULT_COLLECTION_CLASS = () -> ArrayList.class;

    static final Supplier<Integer> DEFAULT_COLLECTION_SIZE = () -> 10;

    static final Supplier<Class<?>> DEFAULT_COLLECTION_ITEM_CLASS = () -> Object.class;

    @Override
    public Supplier<Long> maxGenerationDeep() {
        return DEFAULT_MAX_GENERATION_DEEP;
    }

    @Override
    public Supplier<Class<? extends Collection>> defaultCollectionClass() {
        return DEFAULT_COLLECTION_CLASS;
    }

    @Override
    public Supplier<Integer> defaultCollectionSize() {
        return DEFAULT_COLLECTION_SIZE;
    }

    @Override
    public Supplier<Class<?>> defaultCollectionItemClass() {
        return DEFAULT_COLLECTION_ITEM_CLASS;
    }
}
