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
package org.genthz.configuration.dsl.polina;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.apache.commons.lang3.ArrayUtils;
import org.genthz.configuration.dsl.Path;
import org.genthz.configuration.dsl.Selector;

import java.util.ArrayDeque;
import java.util.Deque;

class Antrl4PathProcessor {

    public static <T> Path<T> process(Selector<?> prev, String path) {
        final CharStream stream = CharStreams.fromString(path);
        final PathLexer lexer = new PathLexer(stream);
        final CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        final PathParser parser = new PathParser(tokenStream);
        final ParseTreeWalker walker = new ParseTreeWalker();
        final Listener listener = new Listener(prev);

        walker.walk(listener, parser.path());

        return (Path) listener.prev;
    }

    private static class Listener extends PathBaseListener {
        private Selector<?> prev;

        private Deque<Object> stack = new ArrayDeque<>();

        private Listener(Selector<?> prev) {
            this.prev = prev;
        }

        @Override
        public void enterRoot(PathParser.RootContext ctx) {
            this.prev = new PathRootSelector(this.prev);
        }

        @Override
        public void enterStaticSequence(PathParser.StaticSequenceContext ctx) {
            final String[] path = ctx.staticElement()
                    .stream()
                    .map(e -> e.getText())
                    .toArray(size -> new String[size]);

            ArrayUtils.reverse(path);

            this.prev = new PathStaticElementSelector(
                    this.prev,
                    path
            );
        }

        @Override
        public void enterSkipSequnce(PathParser.SkipSequnceContext ctx) {
            this.prev = new PathSkipElementSelector(
                    this.prev,
                    ctx.skipElement()
                            .stream()
                            .map(e -> e.SKIP_NAME_COUNT() != null ? Integer.parseInt(e.SKIP_NAME_COUNT().getText()) : 1)
                            .reduce(0, (l, r) -> l + r, (l, r) -> l + r)
            );
        }

        @Override
        public void enterMatchedElement(PathParser.MatchedElementContext ctx) {
            this.prev = new PathMatchedElementSelector(this.prev, ctx.getText());
        }
    }
}
