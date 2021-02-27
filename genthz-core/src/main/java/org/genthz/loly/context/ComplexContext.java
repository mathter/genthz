/*
 * Generated - testing becomes easier
 *
 * Copyright (C) 2020 mathter
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
package org.genthz.loly.context;

import org.genthz.Bindings;
import org.genthz.Context;
import org.genthz.ObjectFactory;

import java.util.ArrayList;
import java.util.Collection;

public abstract class ComplexContext<T> extends ValueContext<T> {

    private Collection<ValueContext<?>> members = new ArrayList<>();

    public ComplexContext(ObjectFactory objectFactory, Bindings bindings) {
        super(objectFactory, bindings);
    }

    public ComplexContext(ObjectFactory objectFactory, ValueContext<?> parent) {
        super(objectFactory, parent);
    }

    @Override
    public Collection<? extends Context<?>> childs() {
        return this.members;
    }
}
