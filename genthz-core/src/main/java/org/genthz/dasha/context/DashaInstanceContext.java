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
package org.genthz.dasha.context;

import org.genthz.ObjectFactory;
import org.genthz.context.Accessor;
import org.genthz.context.Bindings;
import org.genthz.context.Context;
import org.genthz.context.ContextFactory;
import org.genthz.context.InstanceContext;
import org.genthz.context.Stage;
import org.genthz.diagnostic.DiagnosticParameters;
import org.genthz.diagnostic.DiganosticInfo;
import org.genthz.util.StreamUtil;

import java.lang.reflect.Type;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class DashaInstanceContext<T> implements InstanceContext<T>, DiagnosticParameters, DiganosticInfo {
    private final ContextFactory contextFactory;

    private final Accessor<T> accessor;

    private final Context up;

    private final Type type;

    private final Bindings bindings;

    private ObjectFactory objectFactory;

    public DashaInstanceContext(ContextFactory contextFactory,
                                Bindings bindings,
                                Accessor<T> accessor,
                                Context up,
                                Type type) {
        this.contextFactory = Objects.requireNonNull(contextFactory);
        this.bindings = bindings;
        this.accessor = Objects.requireNonNull(accessor);
        this.up = up;
        this.type = type;
    }

    @Override
    public <P extends Context> P up() {
        return (P) this.up;
    }

    @Override
    public Stream<Context> ups() {
        return StreamUtil.of(this.up, Context::up);
    }

    @Override
    public ContextFactory contextFactory() {
        return this.contextFactory;
    }

    @Override
    public Stage stage() {
        return this.accessor.stage();
    }

    @Override
    public void stage(Stage stage) {
        this.accessor.stage(stage);
    }

    @Override
    public T get() {
        return this.accessor.get();
    }

    @Override
    public void set(T value) {
        this.accessor.set(value);
    }

    @Override
    public T instance() {
        final T result;

        switch (this.accessor.stage()) {
            case FILLING:
            case COMPLETE:
                result = this.get();
                break;

            case NEW:
                this.objectFactory.process(this);
                result = this.get();
                break;

            default:
                throw new IllegalStateException("Cycle call!");
        }

        return result;
    }

    @Override
    public Type type() {
        return this.type;
    }

    @Override
    public ObjectFactory objectFactory() {
        return this.objectFactory;
    }

    @Override
    public void objectFactory(ObjectFactory objectFactory) {
        this.objectFactory = objectFactory;
    }

    @Override
    public Bindings bindings() {
        return this.bindings;
    }

    @Override
    public Stream<Parameter> params() {
        return Stream.of(
                Parameter.of("type", this.type()),
                Parameter.of("stage", this.stage()),
                Parameter.of("bindings", this.bindings()),
                Parameter.of("up", this.up())
        );
    }

    @Override
    public String getDiagnosticInfo() {
        return new StringBuilder(this.getClass().getSimpleName())
                .append('{')
                .append(
                        this.params()
                                .map(e -> e.getName() + "=" + e.getValue())
                                .collect(Collectors.joining(","))
                )
                .append('}')
                .toString();
    }

    @Override
    public String toString() {
        return this.getDiagnosticInfo();
    }
}
