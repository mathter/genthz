package org.genthz;

import org.genthz.function.Filler;
import org.genthz.function.InstanceBuilderConsumer;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.Deque;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public interface Defaults {
    public int defaultCollectionSize();

    public InstanceBuilderConsumer<Boolean> defBooleanInstanceBuilder();

    public InstanceBuilderConsumer<Byte> defByteInstanceBuilder();

    public InstanceBuilderConsumer<Short> defShortInstanceBuilder();

    public InstanceBuilderConsumer<Integer> defIntegerInstanceBuilder();

    public InstanceBuilderConsumer<Long> defLongInstanceBuilder();

    public InstanceBuilderConsumer<Float> defFloatInstanceBuilder();

    public InstanceBuilderConsumer<Double> defDoubleInstanceBuilder();

    public InstanceBuilderConsumer<String> defStringInstanceBuilder();

    public InstanceBuilderConsumer<Date> defDateInstanceBuilder();

    public InstanceBuilderConsumer<LocalDate> defLocalDateInstanceBuilder();

    public InstanceBuilderConsumer<LocalTime> defLocalTimeInstanceBuilder();

    public InstanceBuilderConsumer<LocalDateTime> defLocalDateTimeInstanceBuilder();

    public InstanceBuilderConsumer<OffsetTime> defOffsetTimeInstanceBuilder();

    public InstanceBuilderConsumer<OffsetDateTime> defOffsetDateTimeInstanceBuilder();

    public InstanceBuilderConsumer<ZonedDateTime> defZonedDateTimeInstanceBuilder();

    public InstanceBuilderConsumer<ZoneId> defZoneIdInstanceBuilder();

    public InstanceBuilderConsumer<Collection> defCollectionInstanceBuilder();

    public Filler<Collection> defCollectionFiller();

    public InstanceBuilderConsumer<List> defListInstanceBuilder();

    public Filler<List> defListFiller();

    public InstanceBuilderConsumer<Queue> defQueueInstanceBuilder();

    public Filler<Queue> defQueueFiller();

    public InstanceBuilderConsumer<Deque> defDequeInstanceBuilder();

    public Filler<Deque> defDequeFiller();

    public InstanceBuilderConsumer<Set> defSetInstanceBuilder();

    public Filler<Set> defSetFiller();

    public <T> InstanceBuilderConsumer<T> defArrayInstanceBuilder();

    public Filler<?> defArrayFiller();
}
