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
package org.genthz.loly;

import org.genthz.Context;
import org.genthz.Util;

import java.lang.reflect.Constructor;
import java.util.Optional;
import java.util.Random;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class DefaultInstanceBuilder<T> extends CalculatedConstructorBasedInstanceBuilder<T> {

    private static final Random RANDOM = new Random();

    public DefaultInstanceBuilder(final Class<T> clazz) {
        super(DefaultInstanceBuilder.buildPredicate(clazz), null);
    }

    @Override
    public T apply(Context<T> context) {
        final Class<T> clazz = context.clazz();

        return Enum.class.isAssignableFrom(clazz) ? buildEnum(clazz) : super.apply(context);
    }

    private T buildEnum(Class<T> clazz) {
        return Optional.of(Stream.of(clazz.getFields())
                .filter(f -> clazz.equals(f.getType()))
                .collect(Collectors.toList()))
                .map(l -> l.get(RANDOM.nextInt(l.size())))
                .map(f -> {
                    try {
                        return (T) f.get(clazz);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                })
                .get();
    }


    private static <T> Predicate<Constructor<T>> buildPredicate(Class<T> clazz) {
        final Constructor<T>[] constructors = Util.getConstructors(clazz);
        return constructor -> constructors.length == 1 || constructor.getParameterCount() == 0;
    }
}