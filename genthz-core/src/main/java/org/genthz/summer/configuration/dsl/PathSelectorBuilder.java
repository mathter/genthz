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
package org.genthz.summer.configuration.dsl;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.genthz.context.Context;
import org.genthz.Description;
import org.genthz.context.RootContext;
import org.genthz.configuration.dsl.Path;
import org.genthz.loly.PathBaseListener;
import org.genthz.loly.PathLexer;
import org.genthz.loly.PathParser;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Pattern;

import static org.genthz.configuration.dsl.Selector.METRICS_UNIT;

class PathSelectorBuilder<T> {
    private final Dsl dsl;

    private final String path;

    private final Selector<?> next;

    public PathSelectorBuilder(Dsl dsl, String path, Selector<?> next) {
        this.dsl = dsl;
        this.path = path;
        this.next = next;
    }

    public <T, P extends org.genthz.configuration.dsl.Selector<T>, Path> P build() {
        final CharStream stream = CharStreams.fromString(this.path);
        final PathLexer lexer = new PathLexer(stream);
        final CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        final PathParser parser = new PathParser(tokenStream);
        final ParseTreeWalker walker = new ParseTreeWalker();
        final Listener listener = new Listener(this.next);

        walker.walk(listener, parser.path());

        return (P) new PathSelector(this.dsl, listener.last, this.next, this.path);
    }

    private static class Listener extends PathBaseListener {
        private static final long UNDEFINED = -1;

        private Function<Context<?>, Long> metrics;

        private long skipCount = UNDEFINED;

        private Predicate<Context<?>> last;

        public Listener(Selector<?> selector) {
            this.last = selector;
        }

        private void push(Predicate<Context<?>> predicate) {
            this.last = this.last != null ? predicate.and(this.last) : predicate;
        }

        private void pushMetrics(Function<Context<?>, Long> metrics) {
            this.metrics = this.metrics != null ? ctx -> this.metrics.apply(ctx) + metrics.apply(ctx) : metrics;
        }

        @Override
        public void enterRoot(PathParser.RootContext ctx) {
            this.push(new RootPredicate());
            this.pushMetrics(METRICS_UNIT);
        }

        @Override
        public void enterName(PathParser.NameContext ctx) {
            this.push(new NameEqualsPredicate(ctx.getText()));
            this.pushMetrics(METRICS_UNIT);
        }

        @Override
        public void enterMatchedName(PathParser.MatchedNameContext ctx) {
            this.push(new MatchedNamePredicate(Pattern.compile(ctx.getText().replace("*", ".*"))));
            this.pushMetrics(METRICS_UNIT);
        }

        @Override
        public void enterSkip(PathParser.SkipContext ctx) {
            this.skipCount = 0;
        }

        @Override
        public void exitSkip(PathParser.SkipContext ctx) {
            this.push(new SkipPredicate(this.skipCount));
            this.pushMetrics(c -> this.skipCount);

            this.skipCount = -1;
        }

        @Override
        public void exitSkipElememt(PathParser.SkipElememtContext ctx) {
            if (this.skipCount < 0) {
                throw new IllegalStateException("skipCount not initialized!");
            }

            this.skipCount += ctx.SKIP_NAME_COUNT() != null ? Integer.parseInt(ctx.SKIP_NAME_COUNT().getText()) : 1;
        }

        @Override
        public void visitErrorNode(ErrorNode node) {
            throw new RuntimeException("Unexpected string: '" + node.getText() + "'");
        }
    }

    private static class PathDescription implements Description {
        private final String path;

        public PathDescription(String path) {
            this.path = path;
        }

        @Override
        public String toString() {
            return new StringBuilder("Path[").append(this.path).append(']').toString();
        }
    }

    private static class RootPredicate implements Predicate<Context<?>> {
        @Override
        public boolean test(Context<?> context) {
            return RootContext.class.equals(context.getClass());
        }

        @Override
        public Predicate<Context<?>> and(Predicate<? super Context<?>> other) {
            throw new UnsupportedOperationException();
        }

        @Override
        public Predicate<Context<?>> or(Predicate<? super Context<?>> other) {
            throw new UnsupportedOperationException();
        }
    }

    private static class NameEqualsPredicate implements Predicate<Context<?>> {
        private final String element;

        public NameEqualsPredicate(String element) {
            this.element = Objects.requireNonNull(element);
        }

        @Override
        public boolean test(Context<?> context) {
            return this.element.equals(context.name());
        }
    }

    private static class MatchedNamePredicate implements Predicate<Context<?>> {
        private final Pattern pattern;

        public MatchedNamePredicate(Pattern pattern) {
            this.pattern = Objects.requireNonNull(pattern);
        }

        @Override
        public boolean test(Context<?> context) {
            return this.pattern.matcher(context.name()).matches();
        }
    }

    private static class SkipPredicate implements Predicate<Context<?>> {
        private final long count;

        public SkipPredicate(long count) {
            this.count = count;
        }

        @Override
        public boolean test(Context<?> context) {
            return context
                    .stream()
                    .skip(count - 1)
                    .findFirst()
                    .isPresent();
        }
    }

    private static class PathSelector<T> extends UpSelector<T> implements Path {
        private final String path;

        public PathSelector(Dsl dsl, Predicate<Context<?>> predicate, Selector<?> next, String path) {
            super(dsl, predicate, next);
            this.path = path;
        }

        @Override
        public String path() {
            return this.path;
        }
    }
}
