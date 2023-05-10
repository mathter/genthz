package org.genthz;

import org.genthz.function.Filler;
import org.genthz.function.InstanceBuilder;

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

    public InstanceBuilder<Boolean> defBooleanInstanceBuilder();

    public InstanceBuilder<Byte> defByteInstanceBuilder();

    public InstanceBuilder<Short> defShortInstanceBuilder();

    public InstanceBuilder<Integer> defIntegerInstanceBuilder();

    public InstanceBuilder<Long> defLongInstanceBuilder();

    public InstanceBuilder<Float> defFloatInstanceBuilder();

    public InstanceBuilder<Double> defDoubleInstanceBuilder();

    public InstanceBuilder<String> defStringInstanceBuilder();

    public InstanceBuilder<Date> defDateInstanceBuilder();

    public InstanceBuilder<LocalDate> defLocalDateInstanceBuilder();

    public InstanceBuilder<LocalTime> defLocalTimeInstanceBuilder();

    public InstanceBuilder<LocalDateTime> defLocalDateTimeInstanceBuilder();

    public InstanceBuilder<OffsetTime> defOffsetTimeInstanceBuilder();

    public InstanceBuilder<OffsetDateTime> defOffsetDateTimeInstanceBuilder();

    public InstanceBuilder<ZonedDateTime> defZonedDateTimeInstanceBuilder();

    public InstanceBuilder<ZoneId> defZoneIdInstanceBuilder();

    public InstanceBuilder<Collection> defCollectionInstanceBuilder();

    public Filler<Collection> defCollectionFiller();

    public InstanceBuilder<List> defListInstanceBuilder();

    public Filler<List> defListFiller();

    public InstanceBuilder<Queue> defQueueInstanceBuilder();

    public Filler<Queue> defQueueFiller();

    public InstanceBuilder<Deque> defDequeInstanceBuilder();

    public Filler<Deque> defDequeFiller();

    public InstanceBuilder<Set> defSetInstanceBuilder();

    public Filler<Set> defSetFiller();
}
