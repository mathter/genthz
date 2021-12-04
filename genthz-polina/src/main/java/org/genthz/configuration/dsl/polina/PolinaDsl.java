/*
 * GenThz - testing becomes easier
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
package org.genthz.configuration.dsl.polina;

import org.genthz.ObjectFactory;
import org.genthz.configuration.dsl.Defaults;
import org.genthz.configuration.dsl.DefaultsDefault;
import org.genthz.configuration.dsl.Dsl;
import org.genthz.configuration.dsl.Path;
import org.genthz.configuration.dsl.Selector;

import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Predicate;

class PolinaDsl implements Dsl {
    private static final Class<?>[] INTERFACES = {
            Selector.class,
            Path.class,
            org.genthz.configuration.dsl.InstanceBuilderSelectable.class,
            org.genthz.configuration.dsl.FillerSelectable.class
    };

    private final Collection<InstanceBuilderSelectable<?>> instanceBuilderSelectables = new ArrayList<>(100);

    private final Collection<FillerSelectable<?>> fillerSelectables = new ArrayList<>(100);

    public PolinaDsl() {
        this.clear();
    }

    @Override
    public <T> Path<T> path(String path) {
        return this.proxy(Antrl4PathProcessor.process(null, path));
    }

    @Override
    public <T> Selector<T> strict(Class<T> clazz) {
        return this.proxy(new ClassStrictSelector<>(null, clazz));
    }

    @Override
    public <T> Selector<T> unstrict(Class<T> clazz) {
        return this.proxy(new ClassUnstrictSelector<>(null, clazz));
    }

    @Override
    public Selector custom(Predicate predicate) {
        return this.proxy(new CustomSelector(null, predicate));
    }

    @Override
    public ObjectFactory objectFactory() {
        final org.genthz.configuration.dsl.polina.ObjectFactory objectFactory = new org.genthz.configuration.dsl.polina.ObjectFactory(this.defaults());

        for (InstanceBuilderSelectable<?> ibs : this.instanceBuilderSelectables) {
            objectFactory.register(ibs);
        }

        for (FillerSelectable<?> fs : this.fillerSelectables) {
            objectFactory.register(fs);
        }

        return objectFactory;
    }

    @Override
    public void clear() {
        this.instanceBuilderSelectables.clear();
        this.fillerSelectables.clear();
    }

    @Override
    public Defaults defaults() {
        return new DefaultsDefault();
    }


    private <S> S proxy(S object) {
        return (S) Proxy.newProxyInstance(
                PolinaDsl.class.getClassLoader(),
                INTERFACES,
                (proxy, method, args) -> {
                    Object value = method.invoke(object, args);

                    if (value instanceof InstanceBuilderSelectable) {
                        this.instanceBuilderSelectables.add((InstanceBuilderSelectable<?>) value);
                        value = this.proxy((S) value);
                    } else if (value instanceof FillerSelectable) {
                        this.fillerSelectables.add((FillerSelectable<?>) value);
                        value = this.proxy((S) value);
                    } else if (value instanceof Path) {
                        value = this.proxy((S) value);
                    } else if (value instanceof Selector) {
                        value = this.proxy((S) value);
                    }

                    return value;
                }
        );
    }
}
