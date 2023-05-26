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

import org.genthz.context.InstanceContext;

import java.util.Optional;

public interface Tail<T> extends InstanceBuilder<T> {
    public static <T> Tail<T> parent() {
        return ctx -> Optional.ofNullable(ctx.up())
                .map(e -> (InstanceContext<T>) e)
                .map(e -> e.instance())
                .orElseThrow(() -> new IllegalStateException("There is no parent valid value!"));
    }
}
