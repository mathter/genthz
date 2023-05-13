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
package org.genthz.dasha.dsl;

import java.lang.reflect.Type;

abstract class TypeOp<T extends TypeOp<T>> extends SelectorOp<T> {
    protected final Type type;

    protected final Type[] genericTypeArgs;

    public TypeOp(SelectorOp up, Type type, Type... genericTypeArgs) {
        super(up);
        this.type = type;
        this.genericTypeArgs = genericTypeArgs;
    }
}
