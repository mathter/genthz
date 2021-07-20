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
package org.genthz.configuration.dsl;

import org.genthz.function.DefaultFiller;
import org.genthz.function.Filler;

import java.lang.reflect.Member;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class Fillers {
    public static <T> Filler def() {
        return new DefaultFiller();
    }

    public static <T> Filler<T> including(String... fieldNames) {
        return new DefaultFiller<>(new IncludingPredicate(fieldNames));
    }

    public static <T> Filler<T> excluding(String... fieldNames) {
        return new DefaultFiller<>(new IncludingPredicate(fieldNames).negate());
    }

    private static final class IncludingPredicate implements Predicate<Member> {
        private final Set<String> fieldNames;

        public IncludingPredicate(String... fieldNames) {
            this.fieldNames = Optional
                    .ofNullable(fieldNames)
                    .map(Stream::of)
                    .orElse(Stream.empty())
                    .collect(Collectors.toSet());
        }

        @Override
        public boolean test(Member member) {
            return this.fieldNames.contains(member.getName());
        }
    }

    private Fillers() {
    }
}
