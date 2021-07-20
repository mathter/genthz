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

import org.genthz.configuration.dsl.FunctionalFiller;
import org.genthz.configuration.dsl.FunctionalInstanceBuilder;
import org.genthz.context.Context;
import org.genthz.function.Filler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

class DslConfiguration implements org.genthz.configuration.dsl.Configuration {
    private static final Logger LOG = LoggerFactory.getLogger(DslConfiguration.class);

    private final Dsl dsl;

    private String name;

    private Collection<Selectable> selectables = new ArrayList<>();

    public DslConfiguration(Dsl dsl) {
        this.dsl = dsl;
    }

    @Override
    public <T> Selector<T> not(org.genthz.configuration.dsl.Selector<T> selector) {
        return this.dsl.not(selector);
    }

    @Override
    public DslConfiguration reg(org.genthz.configuration.dsl.Selectable selectable) {
        LOG.debug("Register " + selectable.description());
        this.selectables.add((Selectable) Objects.requireNonNull(selectable));

        if (selectable instanceof FunctionalInstanceBuilder) {
            this.selectables.add((Selectable) selectable.selector().filler(Filler.UNIT));
        }

        return this;
    }

    @Override
    public DslConfiguration reg(Collection<org.genthz.configuration.dsl.Selectable> selectables) {
        Objects.requireNonNull(selectables)
                .stream()
                .peek(e -> LOG.debug("Register " + e.description()))
                .map(e -> (Selectable) e)
                .forEach(e -> this.reg(e));

        return this;
    }

    @Override
    public Collection<? extends Selectable> selectables() {
        return new ArrayList<>(this.selectables);
    }

    @Override
    public String name() {
        return this.name;
    }

    @Override
    public DslConfiguration name(String name) {
        this.name = name;

        return this;
    }

    @Override
    public <T> Selector<T> custom(Predicate<Context<?>> predicate) {
        return this.dsl.custom(predicate, null);
    }

    @Override
    public org.genthz.Configuration build() {
        return new Configuration(this.selectables());
    }

    @Override
    public <T> FunctionalInstanceBuilder nonstrict(Function<Context<T>, T> function, Class<T> clazz) {
        return this.dsl.nonstrict(function, clazz, null);
    }

    @Override
    public <T> FunctionalFiller<T> nonstrict(BiFunction<Context<T>, T, T> function, Class<T> clazz) {
        return this.dsl.nonstrict(function, clazz, null);
    }

    @Override
    public <T> Selector<T> nonstrict(Class<T> clazz) {
        return this.dsl.nonstrict(clazz, null);
    }

    @Override
    public <T, P extends org.genthz.configuration.dsl.Selector<T>, Path> P path(String path) {
        return (P) this.dsl.path(path, null);
    }

    @Override
    public <T> FunctionalInstanceBuilder<T> strict(Function<Context<T>, T> function, Class<T> clazz) {
        return this.dsl.strict(function, clazz, null);
    }

    @Override
    public <T> FunctionalFiller<T> strict(BiFunction<Context<T>, T, T> function, Class<T> clazz) {
        return this.dsl.strict(function, clazz, null);
    }

    @Override
    public <T> Selector<T> strict(Class<T> clazz) {
        return this.dsl.strict(clazz, null);
    }
}
