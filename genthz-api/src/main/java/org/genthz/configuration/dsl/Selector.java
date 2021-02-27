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

import org.genthz.Context;
import org.genthz.Filler;
import org.genthz.InstanceBuilder;

import java.util.function.Consumer;
import java.util.function.Function;

public interface Selector extends Strictable, NonStrictable, Pathable, Сustomizable, DefaultFillered {

    public String name();

    public Selector next();

    public default Function<Context<?>, Long> metrics() {
        return (c) -> 1L;
    }

    public Selector metrics(Function<Context<?>, Long> metrics);

    public void use(Consumer<Selector> consumer);

    public <T> Selectable filler(Filler<T> function);

    public <T> Selectable instanceBuilder(InstanceBuilder<T> function);
}
