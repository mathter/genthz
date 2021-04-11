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

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.genthz.Context;
import org.genthz.Description;
import org.genthz.configuration.dsl.Path;

import java.util.Optional;
import java.util.function.Function;
import java.util.regex.Pattern;

import static org.genthz.configuration.dsl.Selector.METRICS_UNIT;
import static org.genthz.configuration.dsl.Selector.METRICS_ZERO;

final class PathSelectorBuilder {

    private PathSelectorBuilder() {
    }

    public static Selector build(org.genthz.configuration.dsl.Selector path, Optional<Selector> next) {
        final CharStream stream = CharStreams.fromString(((Path) path).path());
        final PathLexer lexer = new PathLexer(stream);
        final CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        final PathParser parser = new PathParser(tokenStream);
        final ParseTreeWalker walker = new ParseTreeWalker();
        final Listener listener = new Listener(
                path.name(),
                path.metrics(),
                next != null ? next : Optional.empty(),
                ((Path) path).path()
        );

        walker.walk(listener, parser.path());

        return listener.last.get();
    }

    private static class Listener extends PathBaseListener {
        private static final long UNDEFINED = -1;

        private final String name;

        private final Function<Context<?>, Long> metrics;

        private int index = 0;

        private long skipCount = UNDEFINED;

        private Optional<Selector> last;

        private final String path;

        public Listener(String name, Function<Context<?>, Long> metrics, Optional<Selector> next, String path) {
            this.name = name;
            this.metrics = metrics;
            this.last = next;
            this.path = path;
        }

        @Override
        public void enterRoot(PathParser.RootContext ctx) {
            this.last = Optional.of(
                    new RootMatchSelector(
                            this.name,
                            this.metrics != null ? this.metrics : METRICS_UNIT,
                            this.last,
                            new PathDescription(this.path)
                    )
            );
        }

        @Override
        public void enterName(PathParser.NameContext ctx) {
            this.last = Optional.of(
                    new NameEqualsSelector(
                            this.name + (index++),
                            this.metrics != null ? METRICS_ZERO : METRICS_UNIT,
                            this.last,
                            ctx.getText(),
                            new PathDescription(this.path)
                    )
            );
        }

        @Override
        public void enterMatchedName(PathParser.MatchedNameContext ctx) {
            this.last = Optional.of(
                    new MatchedNameSelector(
                            this.name + (index++),
                            this.metrics != null ? METRICS_ZERO : METRICS_UNIT,
                            this.last,
                            Pattern.compile(ctx.getText().replace("*", ".*")),
                            new PathDescription(this.path)
                    )
            );
        }

        @Override
        public void enterSkip(PathParser.SkipContext ctx) {
            this.skipCount = 0;
        }

        @Override
        public void exitSkip(PathParser.SkipContext ctx) {
            this.last = Optional.of(new SkipSelector(
                            this.name + (index++),
                            this.metrics != null ? METRICS_ZERO : (c) -> this.skipCount,
                            this.last,
                            this.skipCount,
                            new PathDescription(this.path)
                    )
            );

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
}
