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

import org.genthz.ObjectFactory;
import org.genthz.context.AccessorResolver;
import org.genthz.context.ContextFactory;
import org.genthz.context.InstanceContext;
import org.genthz.context.Stage;

import java.util.Collection;

public class DefaultFiller<T> implements Filler<T> {
    private final AccessorResolver accessorResolver;

    public DefaultFiller() {
        this(null);
    }

    public DefaultFiller(AccessorResolver accessorResolver) {
        this.accessorResolver = accessorResolver;
    }

    @Override
    public void fill(InstanceContext<T> context) {
        if (context.get() != null) {
            final ContextFactory contextFactory = context.contextFactory();
            final ObjectFactory objectFactory = context.objectFactory();

            (this.accessorResolver == null
                    ? contextFactory.byProperties(context)
                    : contextFactory.byProperties(context, this.accessorResolver))
                    .stream()
                    .forEach(e -> objectFactory.process(e));
        }
    }

    @Override
    public String toString() {
        return "DefaultFiller{}";
    }
}
