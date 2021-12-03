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
package org.genthz.configuration.dsl;

import org.genthz.context.context.Context;
import org.genthz.ObjectFactory;

import java.util.function.Predicate;

public class AbstractDsl implements Dsl {
    private final Dsl dsl;

    public AbstractDsl() {
        this.dsl = DslFactory.get().dsl();
    }

    public AbstractDsl(Dsl dsl) {
        this.dsl = dsl;
    }

    @Override
    public <T> Selector<T> custom(Predicate<Context<?>> predicate) {
        return this.dsl.custom(predicate);
    }

    @Override
    public ObjectFactory objectFactory() {
        return this.dsl.objectFactory();
    }

    @Override
    public void clear() {
        this.dsl.clear();
    }

    @Override
    public Path path(String path) {
        return this.dsl.path(path);
    }

    @Override
    public <T> Selector<T> strict(Class<T> clazz) {
        return this.dsl.strict(clazz);
    }

    @Override
    public <T> Selector<T> unstrict(Class<T> clazz) {
        return this.dsl.unstrict(clazz);
    }

    @Override
    public Defaults defaults() {
        return this.dsl.defaults();
    }
}
