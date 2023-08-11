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

import org.genthz.context.InstanceContext;
import org.genthz.dsl.Customable;
import org.genthz.dsl.FillerFirst;
import org.genthz.dsl.FillerThen;
import org.genthz.dsl.InstanceBuilderFirst;
import org.genthz.dsl.InstanceBuilderThen;
import org.genthz.dsl.Metric;
import org.genthz.dsl.Pathable;
import org.genthz.dsl.Strictable;
import org.genthz.dsl.Unstricable;
import org.genthz.dsl.Using;
import org.genthz.function.DefaultInstanceBuilder;
import org.genthz.function.Filler;
import org.genthz.function.InstanceBuilder;
import org.genthz.function.Selector;
import org.genthz.function.UnitFiller;
import org.genthz.reflection.reference.GetMethodReference;
import org.genthz.reflection.reference.ReferenceUtil;
import org.genthz.reflection.reference.SetMethodReference;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Predicate;

class UnstrictTypeOp<T> extends TypeOp<UnstrictTypeOp<T>> implements Pathable, Customable, InstanceBuilderFirst<T>, FillerFirst<T>, Metric<UnstrictTypeOp<T>>, Using<UnstrictTypeOp> {
    public UnstrictTypeOp(SelectorOp up, Type type) {
        super(up, type);
    }

    @Override
    public Selector selector() {
        return this.setTo(new UnstrictClassSelector(this.up() != null ? this.up().selector() : null, this.type));
    }

    @Override
    public <T, S extends Pathable & Strictable & Unstricable & InstanceBuilderFirst<T> & FillerFirst<T> & Metric<S> & Using<S>> S custom(Predicate<InstanceContext<T>> predicate) {
        return (S) new CustomOps(this, predicate);
    }

    @Override
    public <S extends Pathable & Strictable & Unstricable & Customable & InstanceBuilderFirst & FillerFirst & Metric<S> & Using<S>> S path(String path) {
        return (S) new PathOp(this, path);
    }

    @Override
    public <S extends Pathable & InstanceBuilderFirst & FillerFirst & Metric<S> & Using<S>> S path(GetMethodReference reference) {
        final Method method = ReferenceUtil.method(reference);
        final String name = ReferenceUtil.propertyName(method);
        final Type type = method.getReturnType();

        return (S) new PathOp(this, name).strict(type);
    }

    @Override
    public <S extends Pathable & InstanceBuilderFirst & FillerFirst & Metric<S> & Using<S>> S path(SetMethodReference reference) {
        final Method method = ReferenceUtil.method(reference);
        final String name = ReferenceUtil.propertyName(method);
        final Type type = method.getGenericParameterTypes()[0];

        return (S) new PathOp(this, name).strict(type);
    }

    @Override
    public InstanceBuilderThen<T> filler(Filler<T> function) {
        return new InstanceBuilderThenOp(this, function);
    }

    @Override
    public FillerThen instanceBuilder(InstanceBuilder<T> function) {
        return new FillerThenOp(this, function);
    }

    @Override
    public void simple() {
        this.dsl().reg(new SimpleOp(this, new DefaultInstanceBuilder<>(), UnitFiller.INSTANCE));
    }

    @Override
    public void simple(InstanceBuilder<T> function) {
        this.dsl().reg(new SimpleOp(this, function, UnitFiller.INSTANCE));
    }

    @Override
    public void use(Consumer<UnstrictTypeOp> consumer) {
        Objects.requireNonNull(consumer).accept(this);
    }
}
