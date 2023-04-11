package org.genthz.util;


import java.util.Objects;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public final class StreamUtil {
    private StreamUtil() {
    }

    /**
     * Create {@linkplain java.util.stream.Stream} for <code>startElement</code> and generating function <code>next</code>.
     *
     * @param startElement start element of the stream.
     * @param next         generation function.
     * @param <T>          type of the objects.
     * @return stream of objects.
     */
    public static final <T> java.util.stream.Stream<T> of(T startElement, Function<T, T> next) {
        return startElement != null ? StreamSupport.stream(new NextSpliterator(startElement, next), false) : Stream.empty();
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
