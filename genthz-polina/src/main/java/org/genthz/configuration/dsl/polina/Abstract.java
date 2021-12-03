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

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.genthz.context.context.Context;
import org.genthz.configuration.Filler;
import org.genthz.configuration.InstanceBuilder;
import org.genthz.configuration.dsl.Customable;
import org.genthz.configuration.dsl.Fillable;
import org.genthz.configuration.dsl.InstanceBuildable;
import org.genthz.configuration.dsl.Path;
import org.genthz.configuration.dsl.Pathable;
import org.genthz.configuration.dsl.Selector;
import org.genthz.configuration.dsl.Strictable;
import org.genthz.configuration.dsl.Unstrictable;

import java.util.function.Predicate;

abstract class Abstract<T> implements
        Strictable,
        Unstrictable,
        Pathable,
        Customable<T>,
        InstanceBuildable<T>,
        Fillable<T> {
    protected abstract Selector<T> selector();

    @Override
    public Selector<T> custom(Predicate<Context<?>> predicate) {
        return new CustomSelector(this.selector(), predicate);
    }

    @Override
    public <T> Path<T> path(String path) {
        return Antrl4PathProcessor.process(this.selector(), path);
    }

    @Override
    public <T> Selector<T> strict(Class<T> clazz) {
        return new ClassStrictSelector<>(this.selector(), clazz);
    }

    @Override
    public <T> Selector<T> unstrict(Class<T> clazz) {
        return new ClassUnstrictSelector<>(this.selector(), clazz);
    }

    @Override
    public FillerSelectable<T> f(Filler<T> filler) {
        return new FillerSelectable(this.selector(), filler);
    }

    @Override
    public InstanceBuilderSelectable<T> ib(InstanceBuilder<T> instanceBuilder) {
        return new InstanceBuilderSelectable<>(this.selector(), instanceBuilder);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, Constants.TO_STRING_STYLE);
    }
}
