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
package org.genthz.configuration.dsl.loly;

import org.genthz.Context;
import org.genthz.Filler;
import org.genthz.configuration.dsl.DefaultFiller;
import org.genthz.InstanceBuilder;
import org.genthz.configuration.dsl.Selectable;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

abstract class Selector implements org.genthz.configuration.dsl.Selector {

    private final Dsl dsl;

    private final String name;

    private Function<Context<?>, Long> metrics;

    private final Selector next;

    public Selector(Dsl dsl, String name, Selector next) {
        this(dsl, name, null, next);
    }

    public Selector(Dsl dsl, String name, Function<Context<?>, Long> metrics, Selector next) {
        this.dsl = dsl;
        this.name = name;
        this.metrics = metrics;
        this.next = next;
    }

    public Dsl getDsl() {
        return dsl;
    }

    public String name() {
        return this.name;
    }

    @Override
    public org.genthz.configuration.dsl.Selector next() {
        return this.next;
    }

    @Override
    public Function<Context<?>, Long> metrics() {
        return this.metrics;
    }

    @Override
    public org.genthz.configuration.dsl.Selector metrics(Function<Context<?>, Long> metrics) {
        this.metrics = Objects.requireNonNull(metrics);

        return this;
    }

    @Override
    public <T> Selectable nonstrict(InstanceBuilder<T> function, Class<T> clazz) {
        return this.dsl.nonstrict(function, clazz, this);
    }

    @Override
    public <T> Selectable nonstrict(org.genthz.Filler<? extends T> function, Class<T> clazz) {
        return this.dsl.nonstrict(function, clazz, this);
    }

    @Override
    public <T> org.genthz.configuration.dsl.Selector nonstrict(Class<T> clazz) {
        return this.dsl.nonstrict(clazz);
    }

    @Override
    public Path path(String path) {
        return this.dsl.path(path, this);
    }

    @Override
    public <T> Selectable strict(InstanceBuilder<T> function, Class<T> clazz) {
        return this.dsl.strict(function, clazz, this);
    }

    @Override
    public <T> Selectable strict(org.genthz.Filler<T> function, Class<T> clazz) {
        return this.dsl.strict(function, clazz, this);
    }


    @Override
    public <T> org.genthz.configuration.dsl.Selector strict(Class<T> clazz) {
        return this.dsl.strict(clazz);
    }

    @Override
    public org.genthz.configuration.dsl.Selector custom(Predicate<Context<?>> predicate) {
        return this.dsl.custom(predicate, this);
    }

    @Override
    public void use(Consumer<org.genthz.configuration.dsl.Selector> consumer) {
        Objects.requireNonNull(consumer).accept(this);
    }

    @Override
    public <T> Selectable filler(Filler<T> function) {
        return this.dsl.filler(function, this);
    }

    @Override
    public <T> Selectable instanceBuilder(InstanceBuilder<T> function) {
        return this.dsl.instanceBuilder(function, this);
    }

    @Override
    public DefaultFiller defaultFiller() {
        return this.dsl.defaultFiller(this);
    }
}
