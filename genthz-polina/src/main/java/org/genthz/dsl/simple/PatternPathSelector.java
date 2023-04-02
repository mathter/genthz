package org.genthz.dsl.simple;

import org.genthz.context.Context;
import org.genthz.dsl.Selector;

import java.util.Objects;
import java.util.Optional;
import java.util.regex.Pattern;

public class PatternPathSelector extends PathSelector {
    private final Pattern pattern;

    public PatternPathSelector(Selector parent, Pattern pattern) {
        super(parent);
        this.pattern = pattern;
    }

    public PatternPathSelector(Selector parent, String pattern) {
        super(parent);
        this.pattern = Pattern.compile(pattern);
    }

    public Pattern getPattern() {
        return pattern;
    }

    @Override
    public boolean test(Context<?, ?, ?> context) {
        boolean result;

        // Check for root
        if ("/".equals(this.pattern)) {
            result = context.parent() == null;
        } else {
            result = Optional.ofNullable(context.node())
                    .filter(e -> e instanceof CharSequence)
                    .map(Object::toString)
                    .map(e -> this.pattern.matcher(e).matches())
                    .orElse(false);
        }

        return result && super.test(context);
    }
}
