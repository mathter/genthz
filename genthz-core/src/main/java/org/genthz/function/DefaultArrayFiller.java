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
import org.genthz.context.Context;
import org.genthz.context.ContextFactory;
import org.genthz.context.InstanceContext;

public class DefaultArrayFiller<T> extends AbstractContainerFiller<T> implements Filler<T> {
    public DefaultArrayFiller() {
        super(context -> {
            final int result;

            if (context instanceof InstanceContext) {
                final Object container = ((InstanceContext<T[]>) context).get();

                if (container.getClass().isArray()) {
                    result = ((T[]) container).length;
                } else {
                    throw new IllegalStateException(container + " must be an array!");
                }
            } else {
                throw new IllegalStateException("Context must be " + InstanceContext.class.getName() + "! Now: " + context);
            }

            return result;
        });
    }

    @Override
    public void fill(InstanceContext<T> context) {
        if (context.get() != null) {
            final ContextFactory contextFactory = context.contextFactory();
            final ObjectFactory objectFactory = context.objectFactory();

            contextFactory.byArray(context, this.containerSize.get(context))
                    .forEach(e -> objectFactory.process(e));
        }
    }
}
