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
import org.genthz.configuration.Filler;
import org.genthz.configuration.dsl.Defaults;
import org.genthz.configuration.dsl.DefaultsDefault;
import org.genthz.configuration.dsl.Dsl;
import org.genthz.configuration.dsl.Path;
import org.genthz.configuration.dsl.Selector;

import java.lang.reflect.Proxy;
import java.util.function.Predicate;

class PolinaDsl implements Dsl {

    private org.genthz.configuration.dsl.polina.ObjectFactory factory;

    public PolinaDsl() {
        this.clear();
    }

    @Override
    public <T> Path<T> path(String path) {
        return this.proxy(Antrl4PathProcessor.process(null, path), Path.class);
    }

    @Override
    public <T> Selector<T> strict(Class<T> clazz) {
        return this.proxy(new ClassStrictSelector<>(null, clazz), Selector.class);
    }

    @Override
    public <T> Selector<T> unstrict(Class<T> clazz) {
        return this.proxy(new ClassUnstrictSelector<>(null, clazz), Selector.class);
    }

    @Override
    public Selector custom(Predicate predicate) {
        return this.proxy(new CustomSelector(null, predicate), Selector.class);
    }

    @Override
    public ObjectFactory objectFactory() {
        return this.factory;
    }

    @Override
    public void clear() {
        this.factory = new org.genthz.configuration.dsl.polina.ObjectFactory(this.defaults());
    }

    @Override
    public Defaults defaults() {
        return new DefaultsDefault();
    }

    private <S extends Selector> S proxy(S selector, Class<S> clazz) {
        return (S) Proxy.newProxyInstance(
                PolinaDsl.class.getClassLoader(),
                new Class[]{clazz},
                (proxy, method, args) -> {
                    Object value = method.invoke(selector, args);
                    final String methodName = method.getName();

                    if (value instanceof InstanceBuilderSelectable) {
                        PolinaDsl.this.factory.register((InstanceBuilderSelectable<?>) value);
                        PolinaDsl.this.factory.register(new FillerSelectable<>(((InstanceBuilderSelectable<?>) value).selector(), Filler.UNIT));
                    } else if (value instanceof FillerSelectable) {
                        PolinaDsl.this.factory.register((FillerSelectable<?>) value);
                    } else if (value instanceof Path) {
                        value = this.proxy((Path) value, Path.class);
                    } else if (value instanceof Selector) {
                        value = this.proxy((Selector) value, Selector.class);
                    }

                    return value;
                }
        );
    }
}
