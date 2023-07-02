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

import org.genthz.context.Bindings;
import org.genthz.context.Context;
import org.genthz.context.ContextFactory;
import org.genthz.context.Node;
import org.genthz.context.NodeInstanceContext;

import java.lang.reflect.Type;
import java.util.Objects;
import java.util.stream.Stream;

class DashaNodeInstanceContext<T, N> extends DashaInstanceContext<T> implements NodeInstanceContext<T, N> {
    private final Node<N> node;

    private final boolean isConstructorParameter;

    public DashaNodeInstanceContext(ContextFactory contextFactory,
                                    InstanceAccessor<T> instanceAccessor,
                                    Context up,
                                    Type type,
                                    Node<N> node) {
        this(contextFactory, instanceAccessor, up, type, node, false);
    }

    public DashaNodeInstanceContext(ContextFactory contextFactory,
                                    InstanceAccessor<T> instanceAccessor,
                                    Context up,
                                    Type type,
                                    Node<N> node,
                                    boolean isConstructorParameter) {
        super(contextFactory, Bindings.bindings(up.bindings()), instanceAccessor, up, type);
        this.node = Objects.requireNonNull(node);
        this.isConstructorParameter = isConstructorParameter;
    }

    @Override
    public N node() {
        return this.node.node();
    }

    @Override
    public Stream<Parameter> params() {
        return Stream.concat(
                super.params(),
                Stream.of(Parameter.of("node", this.node()))
        );
    }

    @Override
    public boolean isConstructorParameter() {
        return this.isConstructorParameter;
    }
}
