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
package org.genthz.configuration.dsl;

import org.genthz.context.context.Context;

import java.util.function.Function;
import java.util.function.Predicate;

public interface Selector<T> extends Predicate<Context<?>>,
        Strictable,
        Unstrictable,
        Pathable,
        Customable<T>,
        InstanceBuildable<T>,
        Fillable<T> {
    public static final Function<Context<?>, Integer> METRICS_ZERO = c -> 0;

    public static final Function<Context<?>, Integer> METRICS_ONE = c -> 1;

    public static final Function<Context<?>, Integer> METRICS_TWO = c -> 2;

    public Selector<T> metrics(Function<Context<?>, Integer> function);

    public Function<Context<?>, Integer> metrics();
}
