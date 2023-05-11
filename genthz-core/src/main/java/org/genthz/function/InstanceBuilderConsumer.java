package org.genthz.function;

import org.genthz.context.InstanceContext;

@FunctionalInterface
public interface InstanceBuilderConsumer<T> {
    public void instance(InstanceContext<T> context);

    public static <T> InstanceBuilderConsumer<T> from(InstanceBuilder<T> function) {
        return new InstanceBuilderConsumer<T>() {
            @Override
            public void instance(InstanceContext<T> context) {
                context.set(function.instance(context));
            }
        };
    }
}
