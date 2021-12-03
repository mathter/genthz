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
import org.genthz.configuration.dsl.Path;
import org.genthz.configuration.dsl.Selector;

import java.util.Objects;
import java.util.function.Function;

class PathStaticElementSelector<T> extends AbstractSelector<T> implements Path<T> {
    private final String[] pathElement;

    public PathStaticElementSelector(Selector<?> prev, String[] pathElement) {
        super(prev);
        this.pathElement = Objects.requireNonNull(pathElement);
    }

    @Override
    public Function<Context<?>, Integer> metrics() {
        return super.metrics() != null ? super.metrics() : c -> this.pathElement.length;
    }

    @Override
    public boolean test(Context<?> context) {

        int test = this.pathElement.length;

        for (String pathElement : this.pathElement) {
            if (context != null && context.name().equals(pathElement)) {
                context = context.parent();
                test--;
            } else {
                break;
            }
        }

        return test == 0 && super.test(context);
    }
}
