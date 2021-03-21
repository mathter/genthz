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
package org.genthz.util;

import java.util.Objects;
import java.util.Optional;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.StreamSupport;

public final class StreamUtil {

    private StreamUtil() {
    }

    public static final <T> java.util.stream.Stream<T> of(T startElement, Function<T, T> next) {
        return StreamSupport.stream(new NextSpliterator(startElement, next), false);
    }

    private static final class NextSpliterator<T> implements Spliterator<T> {
        private final Function<T, T> next;
        private T element;

        public NextSpliterator(T element, Function<T, T> next) {
            this.element = Objects.requireNonNull(element);
            this.next = Objects.requireNonNull(next);
        }

        @Override
        public boolean tryAdvance(Consumer<? super T> action) {
            if (this.element != null) {
                Objects.requireNonNull(action).accept(this.element);
                this.element = this.next.apply(this.element);

                return true;
            }

            return false;
        }

        @Override
        public Spliterator<T> trySplit() {
            return null;
        }

        @Override
        public long estimateSize() {
            return Long.MAX_VALUE;
        }

        @Override
        public int characteristics() {
            return NONNULL & IMMUTABLE & SIZED & CONCURRENT;
        }
    }
}
