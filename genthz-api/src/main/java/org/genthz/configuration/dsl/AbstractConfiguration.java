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

import org.genthz.Context;
import org.genthz.Filler;
import org.genthz.InstanceBuilder;

import java.util.Collection;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class AbstractConfiguration implements Configuration, Specification {

    private final Configuration configuration;

    public AbstractConfiguration() {
        this(DslFactory.dsl());
    }

    public AbstractConfiguration(Dsl dsl) {
        this(dsl.configuration());
    }

    public AbstractConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public Configuration reg(Selectable selectable) {
        return this.configuration.reg(selectable);
    }

    @Override
    public Configuration reg(Selectable... selectables) {
        return this.configuration.reg(selectables);
    }

    @Override
    public Collection<Selectable> selectables() {
        return this.configuration.selectables();
    }

    @Override
    public String name() {
        return this.configuration.name();
    }

    @Override
    public <T> Selectable nonstrict(InstanceBuilder<T> function, Class<T> clazz) {
        return this.configuration.nonstrict(function, clazz);
    }

    @Override
    public <T> Selectable nonstrict(Filler<? extends T> function, Class<T> clazz) {
        return this.configuration.nonstrict(function, clazz);
    }

    @Override
    public <T> Selector nonstrict(Class<T> clazz) {
        return this.configuration.nonstrict(clazz);
    }

    @Override
    public Selector path(String path) {
        return this.configuration.path(path);
    }

    @Override
    public <T> Selectable strict(InstanceBuilder<T> function, Class<T> clazz) {
        return this.configuration.strict(function, clazz);
    }

    @Override
    public <T> Selector strict(Class<T> clazz) {
        return this.configuration.strict(clazz);
    }

    @Override
    public <T> Selectable strict(Filler<T> function, Class<T> clazz) {
        return this.strict(function, clazz);
    }

    @Override
    public Selector custom(Predicate<Context<?>> predicate) {
        return this.configuration.custom(predicate);
    }

    @Override
    public Supplier<Long> maxGenerationDeep() {
        return SpecificationAdapter.DEFAULT_MAX_GENERATION_DEEP;
    }

    @Override
    public Supplier<Class<? extends Collection>> defaultCollectionClass() {
        return SpecificationAdapter.DEFAULT_COLLECTION_CLASS;
    }

    @Override
    public Supplier<Integer> defaultCollectionSize() {
        return SpecificationAdapter.DEFAULT_COLLECTION_SIZE;
    }

    @Override
    public Supplier<Class<?>> defaultCollectionItemClass() {
        return SpecificationAdapter.DEFAULT_COLLECTION_ITEM_CLASS;
    }
}
