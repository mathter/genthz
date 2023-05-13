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
package org.genthz.dasha;

import org.apache.commons.lang3.RandomStringUtils;
import org.genthz.Defaults;
import org.genthz.function.DefaultArrayFiller;
import org.genthz.function.DefaultArrayInstanceBuilderConsumer;
import org.genthz.function.DefaultCollectionFiller;
import org.genthz.function.Filler;
import org.genthz.function.InstanceBuilderConsumer;
import org.genthz.reflection.GenericUtil;

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

    private final InstanceBuilderConsumer defaultArrayInstanceBuilder = new DefaultArrayInstanceBuilderConsumer<>(new GenericUtil(false));
    private final Filler<?> defaultArrayFiller = new DefaultArrayFiller<>();

    private int defaultCollectionSize = 5;

    private final Random random = new Random();

    @Override
    public int defaultCollectionSize() {
        return this.defaultCollectionSize;
    }

    @Override
    public InstanceBuilderConsumer<Boolean> defBooleanInstanceBuilder() {
        return ctx -> ctx.set(this.random.nextBoolean());
    }

    @Override
    public InstanceBuilderConsumer<Byte> defByteInstanceBuilder() {
        final byte[] buf = new byte[1];
        this.random.nextBytes(buf);

        return ctx -> {
            ctx.set(buf[0]);
        };
    }

    @Override
    public InstanceBuilderConsumer<Short> defShortInstanceBuilder() {
        return ctx -> ctx.set((short) this.random.nextInt());
    }

    @Override
    public InstanceBuilderConsumer<Integer> defIntegerInstanceBuilder() {
        return ctx -> ctx.set(this.random.nextInt());
    }

    @Override
    public InstanceBuilderConsumer<Long> defLongInstanceBuilder() {
        return ctx -> ctx.set(this.random.nextLong());
    }

    @Override
    public InstanceBuilderConsumer<Float> defFloatInstanceBuilder() {
        return ctx -> ctx.set(this.random.nextFloat());
    }

    @Override
    public InstanceBuilderConsumer<Double> defDoubleInstanceBuilder() {
        return ctx -> ctx.set(this.random.nextDouble());
    }

    @Override
    public InstanceBuilderConsumer<String> defStringInstanceBuilder() {
        return ctx -> ctx.set(RandomStringUtils.randomAlphabetic(10));
    }

    @Override
    public InstanceBuilderConsumer<Date> defDateInstanceBuilder() {
        return ctx -> ctx.set(new Date());
    }

    @Override
    public InstanceBuilderConsumer<LocalDate> defLocalDateInstanceBuilder() {
        return ctx -> ctx.set(LocalDate.now());
    }

    @Override
    public InstanceBuilderConsumer<LocalTime> defLocalTimeInstanceBuilder() {
        return ctx -> ctx.set(LocalTime.now());
    }

    @Override
    public InstanceBuilderConsumer<LocalDateTime> defLocalDateTimeInstanceBuilder() {
        return ctx -> ctx.set(LocalDateTime.now());
    }

    @Override
    public InstanceBuilderConsumer<OffsetTime> defOffsetTimeInstanceBuilder() {
        return ctx -> ctx.set(OffsetTime.now());
    }

    @Override
    public InstanceBuilderConsumer<OffsetDateTime> defOffsetDateTimeInstanceBuilder() {
        return ctx -> ctx.set(OffsetDateTime.now());
    }

    @Override
    public InstanceBuilderConsumer<ZonedDateTime> defZonedDateTimeInstanceBuilder() {
        return ctx -> ctx.set(ZonedDateTime.now());
    }

    @Override
    public InstanceBuilderConsumer<ZoneId> defZoneIdInstanceBuilder() {
        return ctx -> ctx.set(ZoneId.systemDefault());
    }

    @Override
    public InstanceBuilderConsumer<Collection> defCollectionInstanceBuilder() {
        return ctx -> ctx.set(new ArrayList<>());
    }

    @Override
    public Filler<Collection> defCollectionFiller() {
        return (Filler<Collection>) this.defaultCollectionFiller;
    }

    @Override
    public InstanceBuilderConsumer<List> defListInstanceBuilder() {
        return ctx -> ctx.set(new ArrayList<>());
    }

    @Override
    public Filler<List> defListFiller() {
        return (Filler<List>) this.defaultCollectionFiller;
    }

    @Override
    public InstanceBuilderConsumer<Queue> defQueueInstanceBuilder() {
        return ctx -> ctx.set(new ArrayDeque());
    }

    @Override
    public Filler<Queue> defQueueFiller() {
        return (Filler<Queue>) this.defaultCollectionFiller;
    }

    @Override
    public InstanceBuilderConsumer<Deque> defDequeInstanceBuilder() {
        return ctx -> ctx.set(new ArrayDeque());
    }

    @Override
    public Filler<Deque> defDequeFiller() {
        return (Filler<Deque>) this.defaultCollectionFiller;
    }

    @Override
    public InstanceBuilderConsumer<Set> defSetInstanceBuilder() {
        return ctx -> ctx.set(new HashSet());
    }

    @Override
    public Filler<Set> defSetFiller() {
        return (Filler<Set>) this.defaultCollectionFiller;
    }

    @Override
    public <T> InstanceBuilderConsumer<T> defArrayInstanceBuilder() {
        return this.defaultArrayInstanceBuilder;
    }

    @Override
    public Filler<?> defArrayFiller() {
        return this.defaultArrayFiller;
    }
}
