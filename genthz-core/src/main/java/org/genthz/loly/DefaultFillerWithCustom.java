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
package org.genthz.loly;

import org.genthz.Context;
import org.genthz.Filler;

import java.util.Collection;

public class DefaultFillerWithCustom<T> extends DefaultFiller<T> {
    private final Filler<T> function;

    public DefaultFillerWithCustom(
            Collection includedFieldNames,
            Collection excludedFieldNames,
            Filler<T> function
    ) {
        super(includedFieldNames, excludedFieldNames);
        this.function = function;
    }

    @Override
    public T apply(Context<T> context, T object) {
        object = super.apply(context, object);

        return this.function != null ? this.function.apply(context, object) : object;
    }
}
