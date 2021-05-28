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
import org.genthz.InstanceBuilder;
import org.genthz.configuration.dsl.Configuration;
import org.genthz.configuration.dsl.FunctionalFiller;
import org.genthz.configuration.dsl.FunctionalInstanceBuilder;
import org.genthz.configuration.dsl.Selectable;
import org.genthz.configuration.dsl.Selector;
import org.genthz.configuration.dsl.Specification;
import org.genthz.configuration.dsl.SpecificationAdapter;
import org.genthz.util.NameGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.function.Predicate;

public class ConfigurationImpl implements Configuration {
    private static final Logger LOG = LoggerFactory.getLogger(ConfigurationImpl.class);

    private final String name = NameGenerator.nextName();

    private final Collection<Selectable> selectables = new HashSet<>();
    private final Dsl dsl;
    private Specification specification;

    public ConfigurationImpl(Dsl dsl, Specification specification) {
        this.dsl = dsl;
        this.specification = specification != null ? specification : new SpecificationAdapter();
    }

    @Override
    public Configuration reg(Selectable selectable) {
        this.a(selectable);

        return this;
    }

    @Override
    public Configuration reg(Collection<Selectable> selectables) {
        Objects.requireNonNull(selectables)
                .stream()
                .forEach(e -> this.a(e));

        return this;
    }

    @Override
    public Collection<Selectable> selectables() {
        return Collections.unmodifiableCollection(this.selectables);
    }

    @Override
    public String name() {
        return this.name;
    }

    @Override
    public <T> Selectable byConstructor(Predicate<Constructor<T>> predicate) {
        return this.dsl.byConstructor(predicate);
    }

    @Override
    public <T> FunctionalInstanceBuilder<T> nonstrict(InstanceBuilder<T> function, Class<T> clazz) {
        return this.dsl.nonstrict(function, clazz);
    }

    @Override
    public <T> FunctionalFiller<T> nonstrict(org.genthz.Filler<T> function, Class<T> clazz) {
        return this.dsl.nonstrict(function, clazz);
    }

    @Override
    public <T> org.genthz.configuration.dsl.Selector nonstrict(Class<T> clazz) {
        return this.dsl.nonstrict(clazz);
    }

    @Override
    public org.genthz.configuration.dsl.Selector path(String path) {
        return this.dsl.path(path);
    }

    @Override
    public <T> org.genthz.configuration.dsl.loly.FunctionalInstanceBuilder strict(InstanceBuilder<T> function, Class<T> clazz) {
        return this.dsl.strict(function, clazz);
    }

    @Override
    public <T> org.genthz.configuration.dsl.Selector strict(Class<T> clazz) {
        return this.dsl.strict(clazz);
    }

    @Override
    public <T> FunctionalFiller<T> strict(Filler<T> function, Class<T> clazz) {
        return this.dsl.strict(function, clazz);
    }

    @Override
    public <T> Selector custom(Predicate<Context<?>> predicate) {
        return this.dsl.custom(predicate);
    }

    @Override
    public Selector not(Selector selector) {
        return this.dsl.notSelector((org.genthz.configuration.dsl.loly.Selector) selector, true);
    }

    private Selectable a(Selectable selectable) {
        Objects.requireNonNull(selectable);

        this.selectables.add(selectable);
        LOG.debug("{} named {} were added to the configuration {}.", selectable, selectable.name(), this.name());

        return selectable;
    }
}
