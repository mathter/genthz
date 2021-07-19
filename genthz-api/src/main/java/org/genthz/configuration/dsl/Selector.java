/*
 * Generated - testing becomes easier
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
package org.genthz.configuration.dsl;

import org.genthz.context.Context;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.function.Function;

/**
 * Interface represents selector.
 *
 * @author <a href="mailto:mathter@mail.ru">mathter</a>
 * @version 1.0.0
 * @since 1.0.0
 */
public interface Selector<T> extends
        Strictable,
        NonStrictable,
        Pathable,
        Сustomizable<T>,
        Fillered<T>,
        InstanceBuildered<T>,
        Descriptable {
    public static final Function<Context<?>, Long> METRICS_ZERO = (c) -> 0L;

    public static final Function<Context<?>, Long> METRICS_UNIT = (c) -> 1L;

    public static final Function<Context<?>, Long> METRICS_TWO = (c) -> 2L;

    public String name();

    public Selector<T> name(String name);

    public <NS> Selector<NS> next();

    /**
     * Method returns metrics function.
     *
     * @return metrics function.
     */
    public default Function<Context<?>, Long> metrics() {
        return METRICS_UNIT;
    }

    /**
     * Method sets metrics function.
     *
     * @param metrics metrics function.
     * @return itself.
     */
    public Selector<T> metrics(Function<Context<?>, Long> metrics);

    /**
     * The method sets the function that will be used to accumulate {@linkplain Selectable} that use this selector.
     *
     * @param producer function for creating {@linkplain Selectable} using this selector passed as parameter.
     * @return collection of {@linkplain Selectable}
     */
    public default Collection<Selectable> use(Function<Selector<T>, Collection<Selectable>> producer) {
        return Optional
                .ofNullable(producer)
                .map(e -> Optional
                        .ofNullable(e.apply(this))
                        .orElse(Collections.emptyList())
                )
                .orElse(Collections.emptyList());
    }

    /**
     * Method returns negate selector. All parent selectors are stay as origin.
     *
     * @return negate selector.
     */
    public NegateSelector not();
}
