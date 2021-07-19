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

import org.genthz.configuration.dsl.Configuration;
import org.genthz.configuration.dsl.Specification;
import org.genthz.context.Context;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

class Dsl implements org.genthz.configuration.dsl.Dsl {
    @Override
    public Configuration configuration(Specification specification) {
        return new DslConfiguration(this);
    }

    public <T> FunctionalInstanceBuilder<T> strict(Function<Context<T>, T> function, Class<T> clazz, Selector<?> next) {
        final Selector<T> selector = new Selector(this, new StrictClassPredicate(this.getClass(function, clazz)), next);
        final FunctionalInstanceBuilder<T> instanceBuilder = new FunctionalInstanceBuilder<>(selector, function);

        return instanceBuilder;
    }

    public <T> FunctionalFiller<T> strict(BiFunction<Context<T>, T, T> function, Class<T> clazz, Selector<?> next) {
        final Selector<T> selector = new Selector(this, new StrictClassPredicate(this.getClass(function, clazz)), next);
        final FunctionalFiller filler = new FunctionalFiller(selector, function);

        return filler;
    }

    public <T> Selector<T> strict(Class<T> clazz, Selector<?> next) {
        return new Selector<T>(this, new StrictClassPredicate<>(clazz), next);
    }

    public <T> FunctionalInstanceBuilder<T> nonstrict(Function<Context<T>, T> function, Class<T> clazz, Selector<?> next) {
        final Selector<T> selector = new Selector(this, new NonStrictClassPredicate<>(this.getClass(function, clazz)), next);
        final FunctionalInstanceBuilder<T> instanceBuilder = new FunctionalInstanceBuilder<>(selector, function);

        return instanceBuilder;
    }

    public <T> FunctionalFiller<T> nonstrict(BiFunction<Context<T>, T, T> function, Class<T> clazz, Selector<?> next) {
        final Selector<T> selector = new Selector(this, new NonStrictClassPredicate(this.getClass(function, clazz)), next);
        final FunctionalFiller filler = new FunctionalFiller(selector, function);

        return filler;
    }

    public <T> Selector<T> nonstrict(Class<T> clazz, Selector<?> next) {
        return new Selector<>(this, new NonStrictClassPredicate<>(clazz), next);
    }

    public <T> Selector<T> not(org.genthz.configuration.dsl.Selector<T> selector) {
        return ((Selector<T>) selector).not();
    }

    public <T> FunctionalFiller<T> filler(Selector<T> selector, BiFunction<Context<T>, T, T> function) {
        return new FunctionalFiller<>(selector, function);
    }

    public <T> FunctionalInstanceBuilder<T> instance(Function<Context<T>, T> function, Selector<T> selector) {
        return new FunctionalInstanceBuilder<T>(selector, function);
    }

    public <T> Selector<T> custom(Predicate<Context<?>> predicate, Selector<?> next) {
        return new Selector<>(this, predicate, next);
    }

    public <T, P extends org.genthz.configuration.dsl.Selector<T>, Path> P path(String path, Selector<?> next) {
        return new PathSelectorBuilder<T>(this, path, next).build();
    }

    private <T> Class<T> getClass(BiFunction<Context<T>, T, T> function, Class<T> defaultClass) {
        return this.<T>getClass(function)
                .map(e -> {
                    if (defaultClass != null && !defaultClass.isAssignableFrom(e)) {
                        throw new RuntimeException(defaultClass + " is not assignable from " + e + "!");
                    }
                    return Optional.of(e);
                })
                .orElse(Optional.ofNullable(defaultClass))
                .orElseThrow(() -> new RuntimeException("Can't get class for function " + function));
    }

    private <T> Optional<Class<T>> getClass(BiFunction<Context<T>, T, T> function) {
        return Optional
                .ofNullable(function)
                .map(e -> e.getClass())
                .map(e -> Stream
                        .of(e.getMethods())
                        .filter(m -> "apply".equals(m.getName()) && m.getParameterCount() == 2)
                        .findFirst()
                        .orElseThrow(() -> new RuntimeException("There is no method BiFunction.apply(?, ?) in the " + function + "!"))
                )
                .flatMap(this::getReturnTypeOfApp);
    }

    private <T> Class<T> getClass(Function<Context<T>, T> function, Class<T> defaultClass) {
        return this.<T>getClass(function)
                .map(e -> {
                    if (defaultClass != null && !defaultClass.isAssignableFrom(e)) {
                        throw new RuntimeException(defaultClass + " is not assignable from " + e + "!");
                    }
                    return Optional.of(e);
                })
                .orElse(Optional.ofNullable(defaultClass))
                .orElseThrow(() -> new RuntimeException("Can't get class for function " + function));
    }

    private <T> Optional<Class<T>> getClass(Function<Context<T>, T> function) {
        return Optional
                .ofNullable(function)
                .map(e -> e.getClass())
                .map(e -> {
                    try {
                        return e.getMethod("apply", Context.class);
                    } catch (NoSuchMethodException ex) {
                        throw new RuntimeException("There is no method Function.apply(?)!", ex);
                    }
                })
                .flatMap(this::getReturnTypeOfApp);
    }

    private <T> Optional<Class<T>> getReturnTypeOfApp(Method method) {
        return Optional
                .of(method)
                .map(e -> (Class<T>) e.getReturnType())
                .filter(e -> !Object.class.equals(e));
    }
}
