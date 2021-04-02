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

import java.lang.reflect.Constructor;
import java.util.Collection;
import java.util.Deque;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Abstract class to creation of custom generation configurations.
 *
 * @author <a href="mailto:mathter@mail.ru">mathter</a>
 * @version 1.0.0
 * @since 1.0.0
 */
public class AbstractConfiguration implements Configuration, Specification {

    private final Configuration configuration;

    /**
     * Create generation configuration using default {@linkplain Dsl} engine.
     */
    public AbstractConfiguration() {
        this(DslFactory.dsl());
    }

    /**
     * Create generation configuration using specified {@linkplain Dsl} engine.
     *
     * @param dsl
     */
    public AbstractConfiguration(Dsl dsl) {
        this(dsl.configuration());
    }

    /**
     * Create generation configuration using specified {@linkplain Configuration} as base.
     *
     * @param configuration
     */
    public AbstractConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public Configuration reg(Selectable selectable) {
        return this.configuration.reg(selectable);
    }

    @Override
    public Configuration reg(Collection<Selectable> selectables) {
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
    public <T> Selectable byConstructor(Predicate<Constructor<T>> predicate) {
        return this.configuration.byConstructor(predicate);
    }

    @Override
    public <T> FunctionalInstanceBuilder<T> nonstrict(InstanceBuilder<T> function, Class<T> clazz) {
        return this.configuration.nonstrict(function, clazz);
    }

    @Override
    public <T> FunctionalFiller<T> nonstrict(Filler<T> function, Class<T> clazz) {
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
    public <T> FunctionalInstanceBuilder<T> strict(InstanceBuilder<T> function, Class<T> clazz) {
        return this.configuration.strict(function, clazz);
    }

    @Override
    public <T> Selector strict(Class<T> clazz) {
        return this.configuration.strict(clazz);
    }

    @Override
    public <T> FunctionalFiller<T> strict(Filler<T> function, Class<T> clazz) {
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
    public Supplier<Class<? extends List>> defaultListClass() {
        return SpecificationAdapter.DEFAULT_LIST_CLASS;
    }

    @Override
    public Supplier<Class<? extends Set>> defaultSetClass() {
        return SpecificationAdapter.DEFAULT_SET_CLASS;
    }

    @Override
    public Supplier<Class<? extends Queue>> defaultQueueClass() {
        return SpecificationAdapter.DEFAUT_QUEUE;
    }

    @Override
    public Supplier<Class<? extends Deque>> defaultDequeClass() {
        return SpecificationAdapter.DEFAUT_DEQUE;
    }

    @Override
    public Supplier<Integer> defaultCollectionSize() {
        return SpecificationAdapter.DEFAULT_COLLECTION_SIZE;
    }

    @Override
    public Supplier<Class<?>> defaultCollectionItemClass() {
        return SpecificationAdapter.DEFAULT_COLLECTION_ITEM_CLASS;
    }

    @Override
    public Selector not(Selector selector) {
        return this.configuration.not(selector);
    }
}
