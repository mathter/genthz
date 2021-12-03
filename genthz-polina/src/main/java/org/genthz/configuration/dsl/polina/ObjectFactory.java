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

import org.genthz.context.context.Context;
import org.genthz.configuration.Filler;
import org.genthz.configuration.InstanceBuilder;
import org.genthz.configuration.dsl.Defaults;
import org.genthz.configuration.dsl.InstanceBuilders;
import org.genthz.configuration.dsl.function.EnumInstanceBuilder;
import org.genthz.context.Accessor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

class ObjectFactory implements org.genthz.ObjectFactory {
    private static final Logger LOG = Logger.getLogger(ObjectFactory.class.getName());

    private final Collection<InstanceBuilderSelectable<?>> instanceBuilderSelectables = new ArrayList<>(100);

    private final Collection<FillerSelectable<?>> fillerSelectables = new ArrayList<>(100);

    private final Defaults defaults;

    public ObjectFactory(Defaults defaults) {
        this.defaults = defaults;
    }

    public void register(InstanceBuilderSelectable<?> selectable) {
        this.instanceBuilderSelectables.add(selectable);
        LOG.log(Level.CONFIG, "Instance builder added. {}", selectable);
    }

    public void register(FillerSelectable<?> selectable) {
        this.fillerSelectables.add(selectable);
        LOG.log(Level.CONFIG, "Filler added. {}", selectable);
    }

    @Override
    public <T> T build(Class<T> clazz) {
        return this.build(
                new ObjectContext<>(this, null, clazz, null)
        ).value();
    }

    @Override
    public <T> Context<T> build(Context<T> context) {
        final InstanceBuilder<T> instanceBuilder = this.matchInstantBuilder(context);
        final Filler<T> filler = this.matchFiller(context);
        final Accessor<T> accessor = this.accessor(context);
        final T value = instanceBuilder.apply(context);

        accessor.setInstance(value);
        accessor.setFilled(filler.apply(context, value));

        return context;
    }

    @Override
    public Defaults defaults() {
        return this.defaults;
    }

    private <T> Accessor<T> accessor(Context<T> context) {
        return context instanceof Accessor ? (Accessor<T>) context : new ObjectContext(this, null, context.clazz(), null);
    }

    private <T> InstanceBuilder<T> matchInstantBuilder(Context<T> context) {
        final InstanceBuilder<T> result;

        final List<InstanceBuilderSelectable<?>> candidtes = this.instanceBuilderSelectables
                .stream()
                .filter(e -> e.selector().test(context))
                .sorted((left, right) -> {
                    final Function<Context<?>, Integer> leftMetrics = left.selector().metrics();
                    final Function<Context<?>, Integer> rightMetrics = right.selector().metrics();

                    return leftMetrics.andThen(m -> rightMetrics.apply(context).compareTo(m)).apply(context);
                })
                .collect(Collectors.toList());
        final int size = candidtes.size();

        if (size == 0) {
            result = this.defaultInstanceBuilder(context);
        } else if (size == 1) {
            result = (InstanceBuilder<T>) candidtes.get(0);
        } else {
            if (candidtes.get(0).selector().metrics().apply(context) != candidtes.get(1).selector().metrics().apply(context)) {
                result = (InstanceBuilder<T>) candidtes.get(0);
            } else {
                throw new IllegalStateException("There are more then one instant builder! " + candidtes);
            }
        }

        return result;
    }

    private <T> Filler<T> matchFiller(Context<T> context) {
        final Filler<T> result;

        final List<FillerSelectable<?>> candidtes = this.fillerSelectables
                .stream()
                .filter(e -> e.selector().test(context))
                .sorted((left, right) -> {
                    final Function<Context<?>, Integer> leftMetrics = left.selector().metrics();
                    final Function<Context<?>, Integer> rightMetrics = right.selector().metrics();

                    return leftMetrics.andThen(m -> rightMetrics.apply(context).compareTo(m)).apply(context);
                })
                .collect(Collectors.toList());

        final int size = candidtes.size();

        if (size == 0) {
            result = this.defaultFiller(context);
        } else if (size == 1) {
            result = (Filler<T>) candidtes.get(0);
        } else {
            if (candidtes.get(0).selector().metrics().apply(context) != candidtes.get(1).selector().metrics().apply(context)) {
                result = (Filler<T>) candidtes.get(0);
            } else {
                throw new IllegalStateException("There are more then one instant builder! " + candidtes);
            }
        }

        return result;
    }

    private <T> InstanceBuilder<T> defaultInstanceBuilder(Context<T> context) {
        final InstanceBuilder<T> result;
        final Class<T> clazz = context.clazz();

        if (Enum.class.isAssignableFrom(clazz)) {
            result = new EnumInstanceBuilder();
        } else {
            result = InstanceBuilders.minArgCount();
        }

        return result;
    }

    private <T> Filler<T> defaultFiller(Context<T> context) {
        return new DefaultFiller();
    }
}
