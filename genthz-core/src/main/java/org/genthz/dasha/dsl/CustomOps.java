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

import java.lang.reflect.Type;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class CustomOps<T> extends SelectorOp<CustomOps<T>> implements Pathable, Strictable, Unstricable, InstanceBuilderFirst<T>, FillerFirst<T>, Using<CustomOps> {
    private final Predicate<InstanceContext<T>> predicate;

    public CustomOps(SelectorOp up, Predicate<InstanceContext<T>> predicate) {
        super(up);
        this.predicate = predicate;
    }

    @Override
    public Selector selector() {
        return this.setTo(new CustomSelector(this.up() != null ? this.up().selector() : null, this.predicate));
    }

    @Override
    public <S extends Pathable & Strictable & Unstricable & Customable & InstanceBuilderFirst & FillerFirst & Metric<S> & Using<S>> S path(String path) {
        return (S) new PathOp(this, path);
    }

    @Override
    public <T, S extends Pathable & Customable & InstanceBuilderFirst<T> & FillerFirst<T> & Metric<S> & Using<S>> S strict(Type type, Type... genericTypeArgs) {
        return (S) new StrictTypeOp(this, type);
    }

    @Override
    public <T, S extends Pathable & Customable & InstanceBuilderFirst<T> & FillerFirst<T> & Metric<S> & Using<S>> S unstrict(Type type, Type... genericTypeArgs) {
        return (S) new UnstrictTypeOp(this, type);
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
        final Selector selector = this.up().selector();
        this.dsl().reg(new SimpleOp(this, new DefaultInstanceBuilder<>(), UnitFiller.INSTANCE));
    }

    @Override
    public void simple(InstanceBuilder<T> function) {
        final Selector selector = this.up().selector();
        final DashaDsl dsl = this.dsl();

        this.dsl().reg(new SimpleOp(this, function, UnitFiller.INSTANCE));
    }

    @Override
    public void use(Consumer<CustomOps> consumer) {
        Objects.requireNonNull(consumer).accept(this);
    }
}
