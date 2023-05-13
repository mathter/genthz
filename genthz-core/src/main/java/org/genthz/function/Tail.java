package org.genthz.function;

import org.genthz.context.InstanceContext;

import java.util.Optional;

public interface Tail<T> extends InstanceBuilder<T> {
    public static <T> Tail<T> parent() {
        return ctx -> Optional.ofNullable(ctx.up())
                .map(e -> (InstanceContext<T>) e)
                .map(e -> e.instance())
                .orElseThrow(() -> new IllegalStateException("There is no parent valid context!"));
    }
}
