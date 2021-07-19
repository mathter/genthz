/*
 * Generated - testing becomes easier
 *
 * Copyright (C) 2021 mathter@mail.ru
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
package org.genthz.summer.configuration.dsl;

import org.genthz.Description;
import org.genthz.configuration.dsl.CollectionFiller;
import org.genthz.context.Context;

import java.lang.reflect.Constructor;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

class Selector<T> implements org.genthz.Selector, org.genthz.configuration.dsl.Selector<T> {
    private final Dsl dsl;

    protected final Predicate<Context<?>> predicate;

    protected final Selector<?> next;

    private String name;

    private Function<Context<?>, Long> metrics;

    public Selector(Dsl dsl, Predicate<Context<?>> predicate, Selector<?> next) {
        this.dsl = Objects.requireNonNull(dsl);
        this.predicate = Objects.requireNonNull(predicate);
        this.next = next;
    }

    @Override
    public DefaultFiller<T> defaultFiller() {
        return this.dsl.defaultFiller(this);
    }

    @Override
    public Description description() {
        return null;
    }

    @Override
    public FunctionalFiller<T> filler(BiFunction<Context<T>, T, T> function) {
        return this.dsl.filler(this, function);
    }

    @Override
    public <C> CollectionFiller<T, C> collectionFiller(Class<T> collectionClazz, Class<C> componentClazz, int count) {
        return this.dsl.collectionFiller(this, collectionClazz, componentClazz, count);
    }

    @Override
    public FunctionalInstanceBuilder<T> instance(Function<Context<T>, T> function) {
        return this.dsl.instance(function, this);
    }

    @Override
    public ConstructorBasedInstanceBuilder<T> byConstructor(Predicate<Constructor<T>> predicate) {
        return this.dsl.byConstructor(predicate, this);
    }

    @Override
    public <T> FunctionalInstanceBuilder nonstrict(Function<Context<T>, T> function, Class<T> clazz) {
        return this.dsl.nonstrict(function, clazz, this);
    }

    @Override
    public <T> FunctionalFiller<T> nonstrict(BiFunction<Context<T>, T, T> function, Class<T> clazz) {
        return this.dsl.nonstrict(function, clazz, this);
    }

    @Override
    public <T> org.genthz.configuration.dsl.Selector<T> nonstrict(Class<T> clazz) {
        return this.dsl.nonstrict(clazz, this);
    }

    @Override
    public <T, P extends org.genthz.configuration.dsl.Selector<T>, Path> P path(String path) {
        return (P) this.dsl.path(path, this);
    }

    @Override
    public String name() {
        return this.name;
    }

    @Override
    public org.genthz.configuration.dsl.Selector<T> name(String name) {
        this.name = name;
        return this;
    }

    @Override
    public <NS> Selector<NS> next() {
        return (Selector<NS>) this.next;
    }

    @Override
    public Function<Context<?>, Long> metrics() {
        return this.metrics != null ? this.metrics : org.genthz.configuration.dsl.Selector.super.metrics();
    }

    @Override
    public Selector<T> metrics(Function<Context<?>, Long> metrics) {
        this.metrics = metrics;
        return this;
    }

    @Override
    public NegateSelector<T> not() {
        return new NegateSelector(this.dsl, this.predicate.negate(), this.next, this);
    }

    @Override
    public <T> FunctionalInstanceBuilder<T> strict(Function<Context<T>, T> function, Class<T> clazz) {
        return this.dsl.strict(function, clazz, this);
    }

    @Override
    public <T> FunctionalFiller<T> strict(BiFunction<Context<T>, T, T> function, Class<T> clazz) {
        return this.dsl.strict(function, clazz, this);
    }

    @Override
    public <T> org.genthz.configuration.dsl.Selector<T> strict(Class<T> clazz) {
        return this.dsl.strict(clazz, this);
    }

    @Override
    public Selector<T> custom(Predicate<Context<?>> predicate) {
        return this.dsl.custom(predicate, this);
    }

    @Override
    public boolean test(Context<?> context) {
        return this.predicate.test(context) && (this.next == null || this.next.test(context));
    }
}
