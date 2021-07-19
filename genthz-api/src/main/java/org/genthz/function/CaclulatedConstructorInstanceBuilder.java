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
package org.genthz.function;

import org.genthz.NewInstanceException;
import org.genthz.context.Context;

import java.lang.reflect.Constructor;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CaclulatedConstructorInstanceBuilder<T> extends AbstractConstructorInstanceBuilder<T> {

    private final Predicate<Constructor<T>> predicate;

    public CaclulatedConstructorInstanceBuilder(Predicate<Constructor<T>> predicate) {
        this.predicate = predicate;
    }

    protected Constructor<T> getConstructor(Context<T> context) {
        return Optional.of(Stream
                .of((Constructor<T>[]) context.clazz().getDeclaredConstructors())
                .filter(this.predicate)
                .collect(Collectors.toList()))
                .map(c -> {
                    if (c.size() == 0) {
                        throw new NewInstanceException(context, "Constructor not found!");
                    } else if (c.size() > 1) {
                        throw new NewInstanceException(context, "More then one constructor found using predicate=" + this.predicate + "!");
                    } else {
                        return c.get(0);
                    }
                })
                .get();
    }
}
