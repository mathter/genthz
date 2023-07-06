/*
 * Generated - testing becomes easier
 *
 * Copyright (C) 2023 mathter@mail.ru
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
package org.genthz.dasha.function;

import org.genthz.FieldMatchers;
import org.genthz.dasha.context.DashaAccessorResolver;
import org.genthz.function.DefaultFiller;
import org.genthz.function.FieldMatcher;
import org.genthz.function.Filler;

import java.util.Collection;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Predefined filles.
 */
public abstract class Fillers {
    /**
     * Method creates filler for filling selected fields.
     *
     * @param fieldNames field names for filling.
     * @param <T>        type of object to be created.
     * @return filler.
     */
    public static <T> Filler<T> includes(String... fieldNames) {
        return new DefaultFiller(
                Optional.ofNullable(fieldNames)
                        .map(Stream::of)
                        .map(e -> e.map(ee -> (FieldMatcher) FieldMatchers.name(ee))
                                .filter(Objects::nonNull)
                                .collect(Collectors.toList()))
                        .map(e -> new DashaAccessorResolver(e, null))
                        .orElse(new DashaAccessorResolver())

        );
    }

    /**
     * Method creates a filler for filling fields selected by field mathchers.
     *
     * @param fieldMatchers field matchers.
     * @param <T>           type of object to be created.
     * @return filler.
     */
    public static <T> Filler<T> includes(FieldMatcher... fieldMatchers) {
        return new DefaultFiller<>(new DashaAccessorResolver(
                Optional.ofNullable(fieldMatchers)
                        .map(Stream::of)
                        .orElse(Stream.empty())
                        .collect(Collectors.toList()),
                null
        ));
    }

    /**
     * Method creates a filler for filling fields selected by field mathchers.
     *
     * @param fieldMatchers field matchers.
     * @param <T>           type of object to be created.
     * @return filler.
     */
    public static <T> Filler<T> includes(Collection<? extends FieldMatcher> fieldMatchers) {
        return new DefaultFiller<>(new DashaAccessorResolver(fieldMatchers, null));
    }

    /**
     * Method creates filler for filling fields excluding ones selected by parameter.
     *
     * @param fieldNames field names for excluding from filling.
     * @param <T>        type of object to be created.
     * @return filler.
     */
    public static <T> Filler<T> excludes(String... fieldNames) {
        return new DefaultFiller(
                Optional.ofNullable(fieldNames)
                        .map(Stream::of)
                        .map(e -> e.map(ee -> (FieldMatcher) FieldMatchers.name(ee))
                                .filter(Objects::nonNull)
                                .collect(Collectors.toList()))
                        .map(e -> new DashaAccessorResolver(null, e))
                        .orElse(new DashaAccessorResolver())

        );
    }

    /**
     * Method creates filler for filling fields excluding ones selected by field matchers.
     *
     * @param fieldMatchers field matchers for excluding fields from filling.
     * @param <T>           type of object to be created.
     * @return filler.
     */
    public static <T> Filler<T> excludes(FieldMatcher... fieldMatchers) {
        return new DefaultFiller<>(new DashaAccessorResolver(
                null,
                Optional.ofNullable(fieldMatchers)
                        .map(Stream::of)
                        .orElse(Stream.empty())
                        .collect(Collectors.toList())
        ));
    }

    /**
     * Method creates filler for filling fields excluding ones selected by field matchers.
     *
     * @param fieldMatchers field matchers for excluding fields from filling.
     * @param <T>           type of object to be created.
     * @return filler.
     */
    public static <T> Filler<T> excludes(Collection<? extends FieldMatcher> fieldMatchers) {
        return new DefaultFiller<>(new DashaAccessorResolver(null, fieldMatchers));
    }

    private Fillers() {
    }
}
