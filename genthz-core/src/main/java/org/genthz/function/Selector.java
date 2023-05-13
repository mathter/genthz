/*
 * Generated - testing becomes easier
 *
 * Copyright (C) 2023 mathter@mail.ru
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
package org.genthz.function;

import org.genthz.context.Context;
import org.genthz.context.InstanceContext;
import org.genthz.dsl.Metric;
import org.genthz.dsl.Named;

import java.util.Optional;
import java.util.function.Predicate;

/**
 * The interface represents predicate for {@linkplain InstanceContext}. Method {@linkplain Predicate#test(Object)} returns
 * <code>true</code> if {@linkplain InstanceContext} satisfies the predicate conditions.
 */
public interface Selector extends Predicate<Context>, Named<Selector>, Metric<Selector> {
    public Optional<Selector> up();
}
