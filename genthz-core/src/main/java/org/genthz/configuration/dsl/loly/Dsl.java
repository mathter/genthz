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
import org.genthz.configuration.dsl.DefaultFiller;
import org.genthz.util.NameGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.genthz.configuration.dsl.Configuration;
import org.genthz.configuration.dsl.Specification;

import java.util.Objects;
import java.util.function.Predicate;

class Dsl implements org.genthz.configuration.dsl.Dsl {

    private static final Logger LOG = LoggerFactory.getLogger(Dsl.class);

    @Override
    public Path path(String path) {
        return this.path(path, null);
    }

    public Path path(String path, Selector next) {
        Objects.requireNonNull(path);

        final String name = NameGenerator.nextName();
        LOG.debug("Constract path selector '{}' for '{}'", name, path);
        return new Path(this, name, next, path);
    }

    @Override
    public Selector custom(Predicate<Context<?>> predicate) {
        return this.custom(predicate, null);
    }

    public Selector custom(Predicate<Context<?>> predicate, Selector next) {
        Objects.requireNonNull(predicate);

        final String name = NameGenerator.nextName();
        LOG.debug("Constract custom selector '{}' for '{}'", name, predicate);
        return new CustomSelector(this, name, next, predicate);
    }

    @Override
    public <T> InstanceBuilder nonstrict(org.genthz.InstanceBuilder<T> function, Class<T> clazz) {
        return this.nonstrict(function, clazz, null);
    }

    public <T> InstanceBuilder nonstrict(org.genthz.InstanceBuilder<T> function, Class<T> clazz, Selector next) {
        return this.instanceBuilder(function, this.nonstrict(clazz, next));
    }

    @Override
    public <T> Selector nonstrict(Class<T> clazz) {
        Objects.requireNonNull(clazz);

        final String name = NameGenerator.nextName();
        LOG.debug("Constract strict selector '{}' for '{}'", name, clazz);
        return new ClassSelector.NonStrict(this, name, null, clazz);
    }

    public <T> Selector nonstrict(Class<T> clazz, Selector next) {
        Objects.requireNonNull(clazz);

        final String name = NameGenerator.nextName();
        LOG.debug("Constract strict selector '{}' for '{}'", name, clazz);
        return new ClassSelector.NonStrict(this, name, next, clazz);
    }

    @Override
    public <T> Filler nonstrict(org.genthz.Filler<? extends T> function, Class<T> clazz) {
        return this.nonstrict(function, clazz, null);
    }

    public <T> Filler nonstrict(org.genthz.Filler<? extends T> function, Class<T> clazz, Selector next) {
        return this.filler(function, this.nonstrict(clazz, next));
    }

    @Override
    public <T> InstanceBuilder strict(org.genthz.InstanceBuilder<T> function, Class<T> clazz) {
        return this.strict(function, clazz, null);
    }

    public <T> InstanceBuilder strict(org.genthz.InstanceBuilder<T> function, Class<T> clazz, Selector next) {
        return this.instanceBuilder(function, this.strict(clazz, next));
    }

    @Override
    public <T> Filler strict(org.genthz.Filler<T> function, Class<T> clazz) {
        return this.strict(function, clazz, null);
    }

    public <T> Filler strict(org.genthz.Filler<T> function, Class<T> clazz, Selector next) {
        return this.filler(function, this.strict(clazz, next));
    }

    @Override
    public <T> Selector strict(Class<T> clazz) {
        return this.strict(clazz, null);
    }

    public <T> Selector strict(Class<T> clazz, Selector next) {
        Objects.requireNonNull(clazz);

        final String name = NameGenerator.nextName();
        LOG.debug("Constract strict selector '{}' for '{}'", name, clazz);
        return new ClassSelector.Strict(this, name, next, clazz);
    }

    public <T> Filler filler(org.genthz.Filler<T> function, Selector selector) {
        Objects.requireNonNull(function);
        Objects.requireNonNull(selector);

        final String name = NameGenerator.nextName();
        LOG.debug("Constract strict filler '{}' for '{}' with '{}'", name, function, selector);
        return new org.genthz.configuration.dsl.loly.Filler(function, selector);
    }

    public <T> InstanceBuilder instanceBuilder(org.genthz.InstanceBuilder<T> function, Selector selector) {
        Objects.requireNonNull(function);
        Objects.requireNonNull(selector);

        final String name = NameGenerator.nextName();
        LOG.debug("Constract strict instanceBuilder '{}' for '{}' with '{}'", name, function, selector);
        return new InstanceBuilder(function, selector);
    }

    public DefaultFiller defaultFiller(Selector selector) {
        return new org.genthz.configuration.dsl.loly.DefaultFiller(selector);
    }

    @Override
    public Configuration configuration() {
        return this.configuration(null);
    }

    @Override
    public Configuration configuration(Specification specification) {
        return new ConfigurationImpl(this, specification);
    }
}
