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
package org.genthz.configuration.dsl.function;

import org.genthz.context.context.Context;
import org.genthz.configuration.InstanceBuilder;
import org.genthz.util.Util;

import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EnumInstanceBuilder<T extends Enum<T>> implements InstanceBuilder<T> {
    private static final Random RANDOM = new Random();

    @Override
    public T apply(Context<T> context) {
        final T result;
        final Class<T> clazz = context.clazz();

        if (clazz.isEnum()) {
            result = Optional.of(Stream.of(clazz.getFields())
                            .filter(f -> clazz.equals(f.getType()))
                            .collect(Collectors.toList()))
                    .map(l -> l.get(RANDOM.nextInt(l.size())))
                    .map(f -> Util.<T>getFieldValue(f, clazz))
                    .get();
        } else {
            throw new IllegalStateException(clazz + " must be Enum!");
        }

        return result;
    }
}