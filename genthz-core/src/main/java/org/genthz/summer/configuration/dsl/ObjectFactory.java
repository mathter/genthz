/*
 * Generated - testing becomes easier
 *
 * Copyright (C) 2021 mathter@mail.ru
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
package org.genthz.summer.configuration.dsl;

import org.genthz.Configuration;
import org.genthz.context.def.AbstractContext;
import org.genthz.context.Accessor;
import org.genthz.context.Bindings;
import org.genthz.context.Context;
import org.genthz.context.Stage;
import org.genthz.function.DefaultFiller;
import org.genthz.function.DefaultInstanceBuilder;
import org.genthz.function.Filler;
import org.genthz.function.InstanceBuilder;
import org.genthz.util.Util;

import java.util.Optional;

class ObjectFactory implements org.genthz.ObjectFactory {
    private final Configuration configuration;

    public ObjectFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public <T> Context<T> build(Context<T> context) {
        return Optional.of(context)
                .map(e -> (Context<T> & Accessor<T>) (e instanceof Accessor ? e : new RootContext<>(e.clazz(), e.bindings())))
                .map(e -> this.buildInternal(e))
                .orElseThrow(() -> new RuntimeException());
    }

    private <T, P extends Context<T> & Accessor<T>> Context<T> buildInternal(P accessor) {
        final InstanceBuilder<T> instanceBuilder = Optional
                .ofNullable(this.configuration.instanceBuilder(accessor))
                .orElseGet(() -> this.defaultBuilder(accessor));
        final Filler<T> filler = Optional
                .ofNullable(this.configuration.filler(accessor))
                .orElseGet(() -> this.defaultFiller(accessor));
        final T value = instanceBuilder.apply(accessor);

        accessor.setInstance(value);
        accessor.setFilled(filler.apply(accessor, value));

        return accessor;
    }

    private <T> InstanceBuilder<T> defaultBuilder(Context<T> context) {
        return new DefaultInstanceBuilder<>(context.clazz());
    }

    private <T> Filler<T> defaultFiller(Context<T> context) {
        return Util.isSimple(context.clazz()) ? Filler.UNIT : new DefaultFiller<>();
    }

    private class RootContext<T> extends AbstractContext<T> implements org.genthz.context.RootContext<T>, Accessor<T> {
        private final Class<T> clazz;

        private T value;

        public RootContext(Class<T> clazz, Bindings bindings) {
            super(ObjectFactory.this, null);
            this.clazz = clazz;
        }

        @Override
        public Class<T> clazz() {
            return this.clazz;
        }

        @Override
        public T node() {
            return this.value;
        }

        @Override
        public String name() {
            return null;
        }

        @Override
        public T get() {
            return this.value;
        }

        @Override
        public void setInstance(T value) {
            this.value = value;
            this.stage(Stage.INSTANCE);
        }

        @Override
        public void setFilled(T value) {
            this.value = value;
            this.stage(Stage.COMPLETE);
        }
    }
}
