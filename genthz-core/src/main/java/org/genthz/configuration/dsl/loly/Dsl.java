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
import org.genthz.configuration.dsl.CollectionFiller;
import org.genthz.configuration.dsl.Configuration;
import org.genthz.configuration.dsl.DefaultFiller;
import org.genthz.configuration.dsl.FunctionalFiller;
import org.genthz.configuration.dsl.Selectable;
import org.genthz.configuration.dsl.Specification;
import org.genthz.util.NameGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Stream;

class Dsl implements org.genthz.configuration.dsl.Dsl {

    private static final Logger LOG = LoggerFactory.getLogger(Dsl.class);

    @Override
    public <T> Selectable byConstructor(Predicate<Constructor<T>> predicate) {
        throw new UnsupportedOperationException();
    }

    public <T> ConstructorBasedInstanceBuilder<T> byConstructor(Predicate<Constructor<T>> predicate, Selector next) {
        return new ConstructorBasedInstanceBuilder<T>(next, predicate);
    }

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
    public <T> Selector custom(Predicate<Context<?>> predicate) {
        return this.custom(predicate, null);
    }

    public Selector custom(Predicate<Context<?>> predicate, Selector next) {
        Objects.requireNonNull(predicate);

        final String name = NameGenerator.nextName();
        LOG.debug("Constract custom selector '{}' for '{}'", name, predicate);
        return new CustomSelector(this, name, next, predicate);
    }

    @Override
    public <T> FunctionalInstanceBuilder nonstrict(org.genthz.InstanceBuilder<T> function, Class<T> clazz) {
        return this.nonstrict(function, clazz, null);
    }

    public <T> FunctionalInstanceBuilder nonstrict(org.genthz.InstanceBuilder<T> function, Class<T> clazz, Selector next) {
        return this.instanceBuilder(function, this.nonstrict(clazz != null ? clazz : classOf(function), next));
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
    public <T> org.genthz.configuration.dsl.FunctionalFiller<T> nonstrict(org.genthz.Filler<T> function, Class<T> clazz) {
        return this.nonstrict(function, clazz, null);
    }

    public <T> org.genthz.configuration.dsl.FunctionalFiller<T> nonstrict(org.genthz.Filler<T> function, Class<T> clazz, Selector next) {
        return this.filler(function, this.nonstrict(clazz != null ? clazz : classOf(function), next));
    }

    @Override
    public <T> FunctionalInstanceBuilder strict(org.genthz.InstanceBuilder<T> function, Class<T> clazz) {
        return this.strict(function, clazz, null);
    }

    public <T> FunctionalInstanceBuilder strict(org.genthz.InstanceBuilder<T> function, Class<T> clazz, Selector next) {
        return this.instanceBuilder(function, this.strict(clazz, next));
    }

    @Override
    public <T> FunctionalFiller<T> strict(org.genthz.Filler<T> function, Class<T> clazz) {
        return this.strict(function, clazz, null);
    }

    public <T> FunctionalFiller<T> strict(org.genthz.Filler<T> function, Class<T> clazz, Selector next) {
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

    public <T> org.genthz.configuration.dsl.FunctionalFiller<T> filler(org.genthz.Filler<T> function, Selector selector) {
        Objects.requireNonNull(function);
        Objects.requireNonNull(selector);

        final String name = NameGenerator.nextName();
        LOG.debug("Constract strict filler '{}' for '{}' with '{}'", name, function, selector);
        return new org.genthz.configuration.dsl.loly.FunctionalFiller(function, selector);
    }

    public <T> FunctionalInstanceBuilder instanceBuilder(org.genthz.InstanceBuilder<T> function, Selector selector) {
        Objects.requireNonNull(function);
        Objects.requireNonNull(selector);

        final String name = NameGenerator.nextName();
        LOG.debug("Constract strict instanceBuilder '{}' for '{}' with '{}'", name, function, selector);
        return new FunctionalInstanceBuilder(function, selector);
    }

    public DefaultFiller defaultFiller(Selector selector) {
        Objects.requireNonNull(selector);

        final DefaultFiller filler = new org.genthz.configuration.dsl.loly.DefaultFiller(selector);
        LOG.debug("Constract DefaultFiller '{}' with '{}'", filler, selector);
        return new org.genthz.configuration.dsl.loly.DefaultFiller(selector);
    }

    public <T, C> CollectionFiller collectionFiller(
            Selector selector,
            Class<T> collectionClass,
            Class<C> componentClass,
            int count
    ) {
        Objects.requireNonNull(selector);
        Objects.requireNonNull(componentClass);

        final CollectionFiller<T, C> filler = new org.genthz.configuration.dsl.loly.CollectionFiller(selector, collectionClass, componentClass, count);
        LOG.debug("Constract DefaultFiller '{}' with '{}'", filler, selector);
        return filler;
    }

    public NegateSelector notSelector(Selector selector, boolean negateChain) {
        return new NegateSelector(this, selector, negateChain);
    }

    @Override
    public Configuration configuration(Specification specification) {
        return new ConfigurationImpl(this, specification);
    }

    private <T> Class<T> classOf(Constructor<T> constructor) {
        return constructor.getDeclaringClass();
    }

    private <T> Class<T> classOf(org.genthz.InstanceBuilder<T> function) {
        Class<T> clazz = (Class<T>) Stream
                .of(function.getClass().getMethods())
                .filter(m -> "apply".equals(m.getName()))
                .findFirst()
                .map(m -> m.getGenericReturnType())
                .get();

        if (Object.class.equals(clazz)) {
            throw new IllegalStateException("Can't get object class of building by" + function);
        } else {
            return clazz;
        }
    }

    private <T> Class<T> classOf(org.genthz.Filler<? extends T> function) {
        Class<T> clazz = (Class<T>) Stream
                .of(function.getClass().getMethods())
                .filter(m -> "apply".equals(m.getName()))
                .findFirst()
                .map(m -> m.getGenericReturnType())
                .get();

        if (Object.class.equals(clazz)) {
            throw new IllegalStateException("Can't get object class of building by" + function);
        } else {
            return clazz;
        }
    }
}
