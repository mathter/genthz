package org.genthz.summer;

import org.genthz.Context;
import org.genthz.Filler;
import org.genthz.InstanceBuilder;
import org.genthz.Selector;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

class Configuration implements org.genthz.Configuration {

    private final Collection<org.genthz.Selector> selectors = new ArrayList<>();

    private final Map<Selector, InstanceBuilder> instanceBuilders = new HashMap<>();

    private final Map<Selector, Filler> fillers = new HashMap<>();

    public Configuration(Collection<? extends Selectable> selectables) {
        for (Selectable<?> selectable : selectables) {
            final Selector<?> selector = (Selector<?>) selectable.selector();

            this.selectors.add(selector);

            if (selectable instanceof InstanceBuilder) {
                this.instanceBuilders.put(selector, (InstanceBuilder<?>) selectable);
            } else if (selectable instanceof Filler) {
                this.fillers.put(selector, (Filler<?>) selectable);
            } else {
                throw new IllegalStateException(selectable + " must be instance of " + InstanceBuilder.class + " or " + Filler.class + "!");
            }
        }
    }

    @Override
    public <T> Filler<T> filler(org.genthz.Selector selector) {
        return (Filler<T>) this.fillers.get(selector);
    }

    @Override
    public <T> InstanceBuilder<T> instanceBuilder(org.genthz.Selector selector) {
        return (InstanceBuilder<T>) this.instanceBuilders.get(selector);
    }

    @Override
    public <T> Selector matches(Context<T> context) {
        return null;
    }

    private class LocalSelector<T> implements org.genthz.Selector {
        private final Context<T> context;

        public LocalSelector(Context<T> context) {
            this.context = context;
        }

        @Override
        public boolean test(Context<?> context) {
            return this.context.equals(context);
        }
    }
}
