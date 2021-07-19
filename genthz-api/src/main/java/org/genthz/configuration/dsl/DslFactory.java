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
package org.genthz.configuration.dsl;

import java.util.ServiceLoader;
import java.util.stream.StreamSupport;

/**
 * Factiry of the {@linkplain Dsl} engines.
 *
 * @author <a href="mailto:mathter@mail.ru">mathter</a>
 * @version 1.0.0
 * @since 1.0.0
 */
public abstract class DslFactory {
    public static final String DEFAULT_FACTORY = "summer";

    protected DslFactory() {
    }

    /**
     * Method creates and return default {@linkplain Dsl} engine.
     *
     * @return {@linkplain Dsl} engine.
     */
    public static final Dsl dsl() {
        return dsl(DEFAULT_FACTORY);
    }

    /**
     * Method creates and return default {@linkplain Dsl} engine specified by <code>id</code> parameter.
     *
     * @param id factory identifier.
     * @return {@linkplain Dsl} factory.
     */
    public static final Dsl dsl(String id) throws DslFactoryNotFoundException {

        return id != null ? StreamSupport
                .stream(ServiceLoader.load(DslFactory.class).spliterator(), false)
                .filter(f -> id.equals(f.id()))
                .findAny()
                .orElseThrow(() -> new DslFactoryNotFoundException(id))
                .newDsl() : dsl();
    }

    /**
     * Identifier of the factory.
     *
     * @return factory identifier.
     */
    protected abstract String id();

    /**
     * Method create and return {@linkplain Dsl} engine.
     *
     * @return {@linkplain Dsl} engine.
     */
    protected abstract Dsl newDsl();
}
