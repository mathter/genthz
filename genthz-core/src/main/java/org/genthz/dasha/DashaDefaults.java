package org.genthz.dasha;

import org.apache.commons.lang3.RandomStringUtils;
import org.genthz.Defaults;
import org.genthz.function.DefaultCollectionFiller;
import org.genthz.function.Filler;
import org.genthz.function.InstanceBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.Set;

public class DashaDefaults implements Defaults {

    private final Filler<?> defaultCollectionFiller = new DefaultCollectionFiller<>();

    private int defaultCollectionSize = 5;

    private final Random random = new Random();

    @Override
    public int defaultCollectionSize() {
        return this.defaultCollectionSize;
    }

    @Override
    public InstanceBuilder<Boolean> defBooleanInstanceBuilder() {
        return ctx -> ctx.set(this.random.nextBoolean());
    }

    @Override
    public InstanceBuilder<Byte> defByteInstanceBuilder() {
        final byte[] buf = new byte[1];
        this.random.nextBytes(buf);

        return ctx -> {
            ctx.set(buf[0]);
        };
    }

    @Override
    public InstanceBuilder<Short> defShortInstanceBuilder() {
        return ctx -> ctx.set((short) this.random.nextInt());
    }

    @Override
    public InstanceBuilder<Integer> defIntegerInstanceBuilder() {
        return ctx -> ctx.set(this.random.nextInt());
    }

    @Override
    public InstanceBuilder<Long> defLongInstanceBuilder() {
        return ctx -> ctx.set(this.random.nextLong());
    }

    @Override
    public InstanceBuilder<Float> defFloatInstanceBuilder() {
        return ctx -> ctx.set(this.random.nextFloat());
    }

    @Override
    public InstanceBuilder<Double> defDoubleInstanceBuilder() {
        return ctx -> ctx.set(this.random.nextDouble());
    }

    @Override
    public InstanceBuilder<String> defStringInstanceBuilder() {
        return ctx -> ctx.set(RandomStringUtils.randomAlphabetic(10));
    }

    @Override
    public InstanceBuilder<Date> defDateInstanceBuilder() {
        return ctx -> ctx.set(new Date());
    }

    @Override
    public InstanceBuilder<LocalDate> defLocalDateInstanceBuilder() {
        return ctx -> ctx.set(LocalDate.now());
    }

    @Override
    public InstanceBuilder<LocalTime> defLocalTimeInstanceBuilder() {
        return ctx -> ctx.set(LocalTime.now());
    }

    @Override
    public InstanceBuilder<LocalDateTime> defLocalDateTimeInstanceBuilder() {
        return ctx -> ctx.set(LocalDateTime.now());
    }

    @Override
    public InstanceBuilder<OffsetTime> defOffsetTimeInstanceBuilder() {
        return ctx -> ctx.set(OffsetTime.now());
    }

    @Override
    public InstanceBuilder<OffsetDateTime> defOffsetDateTimeInstanceBuilder() {
        return ctx -> ctx.set(OffsetDateTime.now());
    }

    @Override
    public InstanceBuilder<ZonedDateTime> defZonedDateTimeInstanceBuilder() {
        return ctx -> ctx.set(ZonedDateTime.now());
    }

    @Override
    public InstanceBuilder<ZoneId> defZoneIdInstanceBuilder() {
        return ctx -> ctx.set(ZoneId.systemDefault());
    }

    @Override
    public InstanceBuilder<Collection> defCollectionInstanceBuilder() {
        return ctx -> ctx.set(new ArrayList<>());
    }

    @Override
    public Filler<Collection> defCollectionFiller() {
        return (Filler<Collection>) this.defaultCollectionFiller;
    }

    @Override
    public InstanceBuilder<List> defListInstanceBuilder() {
        return ctx -> ctx.set(new ArrayList<>());
    }

    @Override
    public Filler<List> defListFiller() {
        return (Filler<List>) this.defaultCollectionFiller;
    }

    @Override
    public InstanceBuilder<Queue> defQueueInstanceBuilder() {
        return ctx -> ctx.set(new ArrayDeque());
    }

    @Override
    public Filler<Queue> defQueueFiller() {
        return (Filler<Queue>) this.defaultCollectionFiller;
    }

    @Override
    public InstanceBuilder<Deque> defDequeInstanceBuilder() {
        return ctx -> ctx.set(new ArrayDeque());
    }

    @Override
    public Filler<Deque> defDequeFiller() {
        return (Filler<Deque>) this.defaultCollectionFiller;
    }

    @Override
    public InstanceBuilder<Set> defSetInstanceBuilder() {
        return ctx -> ctx.set(new HashSet());
    }

    @Override
    public Filler<Set> defSetFiller() {
        return (Filler<Set>) this.defaultCollectionFiller;
    }
}
