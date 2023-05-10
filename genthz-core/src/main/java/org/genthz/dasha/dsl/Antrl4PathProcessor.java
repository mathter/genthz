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
            this.up = new IndexPathSelector(this.up, Integer.parseInt(ctx.index().getText()));
        }

        @Override
        public void enterSkipElement(PathParser.SkipElementContext ctx) {
            this.up = new SkipPathSelector(this.up, Integer.parseInt(ctx.SKIP_NAME_COUNT().getText()));
        }
    }
}
