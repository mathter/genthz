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
package org.genthz.dasha;

import org.genthz.GenerationProvider;
import org.genthz.ObjectFactory;
import org.genthz.context.Bindings;
import org.genthz.context.ContextFactory;
import org.genthz.context.InstanceContext;
import org.genthz.context.Stage;
import org.genthz.dasha.context.DashaContextFactory;
import org.genthz.dasha.dsl.DashaDsl;
import org.genthz.function.Filler;
import org.genthz.function.InstanceBuilder;

import java.lang.reflect.Type;

public class DashaObjectFactory implements ObjectFactory {
    private final ContextFactory contextFactory;

    private final GenerationProvider generationProvider;

    public DashaObjectFactory() {
        this(null, null);
    }

    public DashaObjectFactory(GenerationProvider generationProvider) {
        this(null, generationProvider);
    }

    public DashaObjectFactory(ContextFactory contextFactory, GenerationProvider generationProvider) {
        this.contextFactory = contextFactory != null ? contextFactory : new DashaContextFactory();
        this.generationProvider = generationProvider != null ? generationProvider : new DashaDsl().def().build();
    }

    @Override
    public <T> T get(Bindings bindings, Class<T> clazz, Type... genericTypes) {
        final InstanceContext<T> context = this.contextFactory.single(bindings, clazz, genericTypes);

        return this.process(context).instance();
    }

    @Override
    public <T> InstanceContext<T> process(InstanceContext<T> context) {
        switch (context.stage()) {
            case NEW:
                this.processNew(context);
                break;

            case COMPLETE:
                break;

            default:
                throw new IllegalStateException(
                        String.format("Context %s stage error!", context)
                );
        }

        return context;
    }

    private <T> void processNew(InstanceContext<T> context) {
        context.objectFactory(this);

        final InstanceBuilder<T> ib = this.generationProvider.instanceBuilder(context);
        final Filler<T> filler = this.generationProvider.filler(context);

        try {
            context.stage(Stage.CREATING);
            context.set(ib.instance(context));
            context.stage(Stage.CREATED);
        } catch (Throwable t) {
            context.stage(Stage.NEW);
            throw new IllegalStateException(
                    String.format("Can't create object instance for context %s", context),
                    t
            );
        }

        try {
            context.stage(Stage.FILLING);
            filler.fill(context);
            context.stage(Stage.COMPLETE);
        } catch (Throwable t) {
            context.stage(Stage.CREATED);
            throw new IllegalStateException(
                    String.format("Can't create object instance for context %s", context),
                    t
            );
        }
    }

    @Override
    public GenerationProvider generationProvider() {
        return this.generationProvider;
    }
}
