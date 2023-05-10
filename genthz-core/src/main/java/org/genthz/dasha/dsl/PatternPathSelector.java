package org.genthz.dasha.dsl;

import org.genthz.context.Context;
import org.genthz.context.NodeInstanceContext;
import org.genthz.function.Selector;

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
    public boolean test(Context context) {
        boolean result;

        if (context instanceof NodeInstanceContext) {
            // Check for root
            if ("/".equals(this.pattern.pattern())) {
                result = context.up() == null;
            } else {

                result = Optional.ofNullable(((NodeInstanceContext) context).node())
                        .filter(e -> e instanceof CharSequence)
                        .map(Object::toString)
                        .map(e -> this.pattern.matcher(e).matches())
                        .orElse(false);
            }
        } else {
            result = false;
        }

        return result && super.

                test(context);
    }
}
