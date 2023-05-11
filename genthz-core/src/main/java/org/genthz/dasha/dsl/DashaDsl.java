package org.genthz.dasha.dsl;

import org.apache.commons.lang3.tuple.Pair;
import org.genthz.Defaults;
import org.genthz.GenerationProvider;
import org.genthz.context.Context;
import org.genthz.dasha.DashaDefaults;
import org.genthz.dsl.Customable;
import org.genthz.dsl.Dsl;
import org.genthz.dsl.FillerFirst;
import org.genthz.dsl.InstanceBuilderFirst;
import org.genthz.dsl.Metric;
import org.genthz.dsl.Pathable;
import org.genthz.dsl.Strictable;
import org.genthz.dsl.Unstricable;
import org.genthz.dsl.Using;
import org.genthz.function.Filler;
import org.genthz.function.InstanceBuilder;
import org.genthz.function.Selector;

import java.lang.reflect.Type;
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
import java.util.Queue;
import java.util.Set;
import java.util.function.Predicate;

public class DashaDsl implements Dsl {
    public static final int DEFAULT_METRIC = 0;

    public static final int DEFAULT_COLLECTION_METRIC = DEFAULT_METRIC;

    public static final int DEFAULT_SUB_COLLECTION_METRIC = DEFAULT_COLLECTION_METRIC + 1;

    private final Collection<Op> ops = new ArrayList<>();

    private Defaults defaults = new DashaDefaults();

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

    public DashaDsl def() {
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
                .m(DEFAULT_COLLECTION_METRIC)
                .ib(this.defaults.defCollectionInstanceBuilder())
                .f(this.defaults.defCollectionFiller());

        this.unstrict(List.class)
                .m(DEFAULT_SUB_COLLECTION_METRIC)
                .ib(this.defaults.defListInstanceBuilder())
                .f(this.defaults.defListFiller());

        this.unstrict(Queue.class)
                .m(DEFAULT_SUB_COLLECTION_METRIC)
                .ib(this.defaults.defQueueInstanceBuilder())
                .f(this.defaults.defQueueFiller());

        this.unstrict(Deque.class)
                .m(DEFAULT_SUB_COLLECTION_METRIC)
                .ib(this.defaults.defDequeInstanceBuilder())
                .filler(this.defaults.defDequeFiller());

        this.unstrict(Set.class)
                .m(DEFAULT_SUB_COLLECTION_METRIC)
                .ib(this.defaults.defSetInstanceBuilder())
                .f(this.defaults.defSetFiller());

        return this;
    }

    @Override
    public <S extends Pathable & Strictable & Unstricable & InstanceBuilderFirst & FillerFirst & Metric<S> & Using<S>> S custom(Predicate<Context> predicate) {
        return (S) new CustomOps(this.empty, predicate);
    }

    @Override
    public <S extends Pathable & Strictable & Unstricable & Customable & InstanceBuilderFirst & FillerFirst & Metric<S> & Using<S>> S path(String path) {
        return (S) new PathOp(this.empty, path);
    }

    @Override
    public <T, S extends Pathable & Customable & InstanceBuilderFirst & FillerFirst & Metric<S> & Using<S>> S strict(Type type, Type... genericTypeArgs) {
        return (S) new StrictTypeOp(this.empty, type, genericTypeArgs);
    }

    @Override
    public <T, S extends Pathable & Customable & InstanceBuilderFirst & FillerFirst & Metric<S> & Using<S>> S unstrict(Type type, Type... genericTypeArgs) {
        return (S) new UnstrictTypeOp(this.empty, type, genericTypeArgs);
    }

    @Override
    public GenerationProvider build(GenerationProvider parent) {
        final Collection<Pair<Selector, InstanceBuilder>> instanceBuilders = new ArrayList<>();
        final Collection<Pair<Selector, Filler>> fillers = new ArrayList<>();

        for (Op op : this.ops) {
            final Collection<Pair<Selector, ?>> list = op.op();
            list.forEach(e -> {
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
}
