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
import org.genthz.InstanceBuilder;
import org.genthz.loly.context.CollectionFakeContext;
import org.genthz.loly.context.Stage;
import org.genthz.loly.context.ValueContext;
import org.genthz.loly.reflect.Accessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CollectionDefaultFiller<T, C> implements Filler<T> {

    private static final Logger LOGGER = LoggerFactory.getLogger(CollectionDefaultFiller.class);

    private final Class<T> collectionClass;

    private final Class<C> componentClass;

    private final Filler<T> custom;

    private final Filler<C> componentCustom;

    private final int count;

    public CollectionDefaultFiller(
            Class<T> collectionClass,
            Class<C> componentClass,
            Filler<T> custom,
            Filler<C> componentCustom,
            int count
    ) {
        this.collectionClass = collectionClass;
        this.componentClass = componentClass;
        this.custom = custom;
        this.componentCustom = componentCustom;
        this.count = count;
    }

    @Override
    public T apply(Context<T> context, T object) {
        final ValueContext<T> collectionContext = (ValueContext<T>) context;

        if (collectionContext.getStage() != Stage.COMPLETE) {
            for (int i = 0; i < this.count; i++) {
                final Context<C> fakeContext = new CollectionFakeContext(
                        collectionContext.objectFactory(),
                        collectionContext.parent().orElse(null),
                        collectionContext,
                        this.componentClass,
                        i
                );
                final InstanceBuilder<C> instanceBuilder = context.objectFactory().instanceBuilder(fakeContext);
                final Filler<C> filler = context.objectFactory().filler(fakeContext);
                final C component = instanceBuilder.apply(context);

                ((Accessor<C>) fakeContext).setInstance(component);

                if (this.componentCustom != null) {
                    filler.apply(fakeContext, component);
                    ((Accessor<C>) fakeContext).setFilled(this.componentCustom.apply(fakeContext, component));
                } else {
                    ((Accessor<C>) fakeContext).setFilled(filler.apply(fakeContext, component));
                }
            }

            if (this.custom != null) {
                collectionContext.setFilled(this.custom.apply(collectionContext, object));
            }

        }

        return object;
    }
}
