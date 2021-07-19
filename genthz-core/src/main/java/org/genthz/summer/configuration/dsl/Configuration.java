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

import org.genthz.context.Context;
import org.genthz.function.Filler;
import org.genthz.function.InstanceBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

class Configuration implements org.genthz.Configuration {
    private static final Logger LOG = LoggerFactory.getLogger(Configuration.class);

    private final Collection<Selector> instanceBuilderSelectors = new ArrayList<>();

    private final Collection<Selector> fillerSelectors = new ArrayList<>();

    private final Map<Selector, InstanceBuilder> instanceBuilders = new HashMap<>();

    private final Map<Selector, Filler> fillers = new HashMap<>();

    public Configuration(Collection<? extends Selectable> selectables) {
        for (Selectable<?> selectable : selectables) {
            final Selector<?> selector = (Selector<?>) selectable.selector();

            if (selectable instanceof InstanceBuilder) {
                this.instanceBuilderSelectors.add(selector);
                this.instanceBuilders.put(selector, (InstanceBuilder<?>) selectable);
            } else if (selectable instanceof Filler) {
                this.fillerSelectors.add(selector);
                this.fillers.put(selector, (Filler<?>) selectable);
            } else {
                throw new IllegalStateException(selectable + " must be instance of " + InstanceBuilder.class + " or " + Filler.class + "!");
            }
        }
    }

    @Override
    public <T> Filler<T> filler(Context<T> context) {
        final Filler<T> result = this.fillerSelectors
                .stream()
                .filter(e -> e.test(context))
                .peek(e -> LOG.trace(e + " filtered for " + context))
                .max(Comparator.comparingLong(e -> (long) e.metrics().apply(context)))
                .map(e -> this.fillers.get(e))
                .orElse(null);

        return result;
    }

    @Override
    public <T> InstanceBuilder<T> instanceBuilder(Context<T> context) {
        final InstanceBuilder<T> result = this.instanceBuilderSelectors
                .stream()
                .filter(e -> e.test(context))
                .max(Comparator.comparingLong(e -> (long) e.metrics().apply(context)))
                .map(e -> this.instanceBuilders.get(e))
                .orElse(null);

        return result;
    }

    @Override
    public ObjectFactory factory() {
        return new ObjectFactory(this);
    }
}
