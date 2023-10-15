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

import org.apache.commons.lang3.tuple.Pair;
import org.genthz.Defaults;
import org.genthz.GenerationProvider;
import org.genthz.ObjectFactory;
import org.genthz.ObjectFactoryProvider;
import org.genthz.context.InstanceContext;
import org.genthz.dasha.DashaDefaults;
import org.genthz.dasha.DashaObjectFactory;
import org.genthz.dsl.Customable;
import org.genthz.dsl.Customs;
import org.genthz.dsl.Dsl;
import org.genthz.dsl.FillerFirst;
import org.genthz.dsl.InstanceBuilderFirst;
import org.genthz.dsl.Metric;
import org.genthz.dsl.Pathable;
import org.genthz.dsl.Strictable;
import org.genthz.dsl.Unstricable;
import org.genthz.dsl.Using;
import org.genthz.function.DefaultFiller;
import org.genthz.function.DefaultInstanceBuilder;
import org.genthz.function.Filler;
import org.genthz.function.InstanceBuilder;
import org.genthz.function.Selector;
import org.genthz.function.UnitFiller;
import org.genthz.logging.Logger;
import org.genthz.logging.LoggerFactory;
import org.genthz.reflection.reference.GetMethodReference;
import org.genthz.reflection.reference.ReferenceUtil;
import org.genthz.reflection.reference.SetMethodReference;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Deque;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.Queue;
import java.util.Set;
import java.util.SortedMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class DashaDsl implements Dsl, ObjectFactoryProvider {
    private static final Logger LOG = LoggerFactory.get();

    public static final int DEFAULT_BASE = Integer.MIN_VALUE;

    public static final int DEFAULT_METRIC = DEFAULT_BASE + 1000;

    public static final int DEFAULT_COLLECTION_METRIC_LEV_0 = DEFAULT_METRIC;

    public static final int DEFAULT_COLLECTION_METRIC_LEV_1 = DEFAULT_COLLECTION_METRIC_LEV_0 + 1;

    public static final int DEFAULT_COLLECTION_METRIC_LEV_2 = DEFAULT_COLLECTION_METRIC_LEV_1 + 1;

    public static final int DEFAULT_COLLECTION_METRIC_LEV_3 = DEFAULT_COLLECTION_METRIC_LEV_2 + 1;

    public static final int DEFAULT_COLLECTION_METRIC_LEV_4 = DEFAULT_COLLECTION_METRIC_LEV_3 + 1;

    public static final int DEFAULT_COLLECTION_METRIC_LEV_5 = DEFAULT_COLLECTION_METRIC_LEV_4 + 1;

    public static final int DEFAULT_COLLECTION_METRIC_LEV_6 = DEFAULT_COLLECTION_METRIC_LEV_5 + 1;

    public static final int DEFAULT_MAX_GENERATION_DEPTH = Integer.MAX_VALUE / 2;

    private final Collection<Op> ops = new ArrayList<>();

    private Defaults defaults = new DashaDefaults();

    private Consumer<Void> def = v -> {
    };

    private SelectorOp<?> empty = new SelectorOp(null) {
        @Override
        public Selector selector() {
            return null;
        }

        @Override
        public Collection<Pair<Selector, ?>> op() {
            return Collections.emptyList();
        }

        @Override
        public DashaDsl dsl() {
            return DashaDsl.this;
        }
    };

    protected void reg(Op op) {
        this.ops.add(op);
    }

    @Override
    public DashaDsl defaults(Defaults defaults) {
        this.defaults = defaults != null ? defaults : new DashaDefaults();
        return this;
    }

    @Override
    public Defaults defaults() {
        return this.defaults;
    }

    public DashaDsl def() {
        this.def = v -> {
            this.unstrict(Object.class)
                    .m(DEFAULT_BASE)
                    .ib(new DefaultInstanceBuilder<>())
                    .f(new DefaultFiller());

            this.strict(Boolean.class)
                    .m(DEFAULT_METRIC)
                    .simple(this.defaults.defBooleanInstanceBuilder());

            this.strict(Boolean.TYPE)
                    .m(DEFAULT_METRIC)
                    .simple(this.defaults.defBooleanInstanceBuilder());

            this.strict(Byte.class)
                    .m(DEFAULT_METRIC)
                    .simple(this.defaults.defByteInstanceBuilder());

            this.strict(Byte.TYPE)
                    .m(DEFAULT_METRIC)
                    .simple(this.defaults.defByteInstanceBuilder());

            this.strict(Short.class)
                    .m(DEFAULT_METRIC)
                    .simple(this.defaults.defShortInstanceBuilder());

            this.strict(Short.TYPE)
                    .m(DEFAULT_METRIC)
                    .simple(this.defaults.defShortInstanceBuilder());

            this.strict(Integer.class)
                    .m(DEFAULT_METRIC)
                    .simple(this.defaults.defIntegerInstanceBuilder());

            this.strict(Integer.TYPE)
                    .m(DEFAULT_METRIC)
                    .simple(this.defaults.defIntegerInstanceBuilder());

            this.strict(Long.class)
                    .m(DEFAULT_METRIC)
                    .simple(this.defaults.defLongInstanceBuilder());

            this.strict(Long.TYPE)
                    .m(DEFAULT_METRIC)
                    .simple(this.defaults.defLongInstanceBuilder());

            this.strict(Float.class)
                    .m(DEFAULT_METRIC)
                    .simple(this.defaults.defFloatInstanceBuilder());

            this.strict(Float.TYPE)
                    .m(DEFAULT_METRIC)
                    .simple(this.defaults.defFloatInstanceBuilder());

            this.strict(Double.class)
                    .m(DEFAULT_METRIC)
                    .simple(this.defaults.defDoubleInstanceBuilder());

            this.strict(Double.TYPE)
                    .m(DEFAULT_METRIC)
                    .simple(this.defaults.defDoubleInstanceBuilder());

            this.strict(Number.class)
                    .m(DEFAULT_BASE + 1)
                    .simple(this.defaults.defNumberInstanceBuilder());

            this.strict(BigInteger.class)
                    .metric(DEFAULT_METRIC)
                    .simple(this.defaults.defBigIntegerInstanceBuilder());

            this.strict(BigDecimal.class)
                    .m(DEFAULT_METRIC)
                    .simple(this.defaults.defBigDecimalInstanceBuilder());

            this.strict(String.class)
                    .m(DEFAULT_METRIC)
                    .simple(this.defaults.defStringInstanceBuilder());

            this.strict(Date.class)
                    .m(DEFAULT_METRIC)
                    .simple(this.defaults.defDateInstanceBuilder());

            this.strict(LocalDate.class)
                    .m(DEFAULT_METRIC)
                    .simple(this.defaults.defLocalDateInstanceBuilder());

            this.strict(LocalDateTime.class)
                    .m(DEFAULT_METRIC)
                    .simple(this.defaults.defLocalDateTimeInstanceBuilder());

            this.strict(LocalTime.class)
                    .m(DEFAULT_METRIC)
                    .simple(this.defaults.defLocalTimeInstanceBuilder());

            this.strict(OffsetTime.class)
                    .m(DEFAULT_METRIC)
                    .simple(this.defaults.defOffsetTimeInstanceBuilder());

            this.strict(OffsetDateTime.class)
                    .m(DEFAULT_METRIC)
                    .simple(this.defaults.defOffsetDateTimeInstanceBuilder());

            this.strict(ZonedDateTime.class)
                    .m(DEFAULT_METRIC)
                    .simple(this.defaults.defZonedDateTimeInstanceBuilder());

            this.strict(ZoneId.class)
                    .m(DEFAULT_METRIC)
                    .simple(this.defaults.defZoneIdInstanceBuilder());

            this.unstrict(Collection.class)
                    .m(DEFAULT_COLLECTION_METRIC_LEV_0)
                    .ib(this.defaults.defCollectionInstanceBuilder())
                    .f(this.defaults.defCollectionFiller());

            this.unstrict(List.class)
                    .m(DEFAULT_COLLECTION_METRIC_LEV_1)
                    .ib(this.defaults.defListInstanceBuilder())
                    .f(this.defaults.defListFiller());

            this.unstrict(Queue.class)
                    .m(DEFAULT_COLLECTION_METRIC_LEV_1)
                    .ib(this.defaults.defQueueInstanceBuilder())
                    .f(this.defaults.defQueueFiller());

            this.unstrict(Deque.class)
                    .m(DEFAULT_COLLECTION_METRIC_LEV_2)
                    .ib(this.defaults.defDequeInstanceBuilder())
                    .filler(this.defaults.defDequeFiller());

            this.unstrict(Set.class)
                    .m(DEFAULT_COLLECTION_METRIC_LEV_1)
                    .ib(this.defaults.defSetInstanceBuilder())
                    .f(this.defaults.defSetFiller());

            this.custom(Customs.isArray())
                    .m(DEFAULT_COLLECTION_METRIC_LEV_0)
                    .ib(this.defaults.defArrayInstanceBuilder())
                    .f(this.defaults.defArrayFiller());

            this.unstrict(Stream.class)
                    .m(DEFAULT_COLLECTION_METRIC_LEV_0)
                    .simple(this.defaults.defStreamInstanceBuilder());

            this.unstrict(Enum.class)
                    .m(DEFAULT_COLLECTION_METRIC_LEV_0)
                    .simple(this.defaults.defEnumInstanceBuilder());

            this.unstrict(Map.class)
                    .m(DEFAULT_COLLECTION_METRIC_LEV_0)
                    .ib(this.defaults.defMapInstanceBuilder())
                    .f(this.defaults.defMapFiller());

            this.unstrict(SortedMap.class)
                    .m(DEFAULT_COLLECTION_METRIC_LEV_1)
                    .ib(this.defaults.defSortedMapInstanceBuilder())
                    .f(this.defaults.defSortedMapFiller());

            this.unstrict(NavigableMap.class)
                    .m(DEFAULT_COLLECTION_METRIC_LEV_2)
                    .ib(this.defaults.defNavigableMapInstanceBuilder())
                    .f(this.defaults.defNavigableMapFiller());

            this.unstrict(ConcurrentMap.class)
                    .m(DEFAULT_COLLECTION_METRIC_LEV_3)
                    .ib(this.defaults.defConcurrentMapInstanceBuilder())
                    .f(this.defaults.defConcurrentMapFiller());

            this.unstrict(ConcurrentNavigableMap.class)
                    .m(DEFAULT_COLLECTION_METRIC_LEV_4)
                    .ib(this.defaults.defConcurrentNavigableMapInstanceBuilder())
                    .f(this.defaults.defConcurrentNavigableMapFiller());

            this.custom(ctx -> ctx.ups().count() >= this.defaults.defaultMaxGenerationDepth().apply(ctx) - 1)
                    .m(DEFAULT_MAX_GENERATION_DEPTH)
                    .f(UnitFiller.INSTANCE);
        };

        return this;
    }

    @Override
    public <T, S extends Pathable & Strictable & Unstricable & InstanceBuilderFirst<T> & FillerFirst<T> & Metric<S> & Using<S>> S custom(Predicate<InstanceContext<T>> predicate) {
        return (S) new CustomOps(this.empty, predicate);
    }

    @Override
    public <S extends Pathable & Strictable & Unstricable & Customable & InstanceBuilderFirst & FillerFirst & Metric<S> & Using<S>> S path(String path) {
        return (S) new PathOp(this.empty, path);
    }

    @Override
    public <O, F, S extends Pathable & InstanceBuilderFirst<F> & FillerFirst<F> & Metric<S> & Using<S>> S path(GetMethodReference<O, F> reference) {
        final Method method = ReferenceUtil.method(reference);
        final String name = ReferenceUtil.propertyName(method);
        final Type type = method.getReturnType();

        return (S) new PathOp(this.empty, name).strict(type);
    }

    @Override
    public <O, F, S extends Pathable & InstanceBuilderFirst<F> & FillerFirst<F> & Metric<S> & Using<S>> S path(SetMethodReference<O, F> reference) {
        final Method method = ReferenceUtil.method(reference);
        final String name = ReferenceUtil.propertyName(method);
        final Type type = method.getGenericParameterTypes()[0];

        return (S) new PathOp(this.empty, name).strict(type);
    }

    @Override
    public <T, S extends Pathable & Customable & InstanceBuilderFirst<T> & FillerFirst<T> & Metric<S> & Using<S>> S strict(Type type, Type... genericTypeArgs) {
        return (S) new StrictTypeOp(this.empty, type);
    }

    @Override
    public <T, S extends Pathable & Customable & InstanceBuilderFirst<T> & FillerFirst<T> & Metric<S> & Using<S>> S unstrict(Type type, Type... genericTypeArgs) {
        return (S) new UnstrictTypeOp(this.empty, type);
    }

    @Override
    public GenerationProvider build(GenerationProvider parent) {
        final Collection<Pair<Selector, InstanceBuilder>> instanceBuilders = new ArrayList<>();
        final Collection<Pair<Selector, Filler>> fillers = new ArrayList<>();

        this.def.accept(null);

        for (Op op : this.ops) {
            final Collection<Pair<Selector, ?>> list = op.op();
            list.stream()
                    .peek(e -> LOG.logCreateSelectable(e))
                    .forEach(e -> {
                        if (e.getRight() instanceof InstanceBuilder) {
                            instanceBuilders.add((Pair<Selector, InstanceBuilder>) e);
                        } else if (e.getRight() instanceof Filler) {
                            fillers.add((Pair<Selector, Filler>) e);
                        } else {
                            throw new IllegalStateException(
                                    String.format("%s is not valid! Must be %s or %s", e, InstanceBuilder.class, Filler.class)
                            );
                        }
                    });
        }

        return new DashaGenerationProvider(
                parent,
                this.defaults,
                instanceBuilders,
                fillers
        );
    }

    @Override
    public ObjectFactory objectFactory() {
        return new DashaObjectFactory(this.build());
    }

    public ObjectFactory objectFactory(GenerationProvider parent) {
        return new DashaObjectFactory(this.build(parent));
    }
}
