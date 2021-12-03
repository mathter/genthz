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

import java.util.ServiceLoader;
import java.util.stream.StreamSupport;

public abstract class DslFactory {
    private static final Object DEFAULT_KEY = "polina";

    public static final DslFactory get() {
        return DslFactory.get(null);
    }

    public static final DslFactory get(Object key) {
        return StreamSupport
                .stream(ServiceLoader.load(DslFactory.class).spliterator(), false)
                .filter(e -> e.key().equals(key))
                .findAny()
                .orElseGet(() -> DslFactory.get(DEFAULT_KEY));
    }

    public abstract Dsl dsl();

    protected abstract Object key();
}
