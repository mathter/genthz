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
package org.genthz.dasha.dsl;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.genthz.function.Selector;

class Antrl4PathProcessor {
    public static final PathSelector path(Selector up, String path) {
        final CharStream stream = CharStreams.fromString(path);
        final PathLexer lexer = new PathLexer(stream);
        final CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        final PathParser parser = new PathParser(tokenStream);
        final ParseTreeWalker walker = new ParseTreeWalker();
        final Listener listener = new Listener(up);

        walker.walk(listener, parser.path());

        return (PathSelector) listener.up;
    }

    private static class Listener extends PathBaseListener {
        private Selector up;

        Listener(Selector up) {
            this.up = up;
        }

        @Override
        public void enterRoot(PathParser.RootContext ctx) {
            this.up = new FixedPathSelector(null, "/");
        }

        @Override
        public void enterStaticElement(PathParser.StaticElementContext ctx) {
            this.up = new FixedPathSelector(this.up, ctx.getText());
        }

        @Override
        public void enterMatchedElement(PathParser.MatchedElementContext ctx) {
            this.up = new PatternPathSelector(this.up, ctx.getText().replaceAll("\\*", ".*"));
        }

        @Override
        public void enterIndexedElement(PathParser.IndexedElementContext ctx) {
            this.up = new IndexPathSelector(this.up, Integer.parseInt(ctx.COUNT().getText()));
        }

        @Override
        public void enterSkipElement(PathParser.SkipElementContext ctx) {
            this.up = new SkipPathSelector(this.up, Integer.parseInt(ctx.COUNT().getText()));
        }
    }
}
