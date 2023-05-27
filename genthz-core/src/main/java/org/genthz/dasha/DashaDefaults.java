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
import org.genthz.context.Context;
import org.genthz.function.DefaultArrayFiller;
import org.genthz.function.DefaultArrayInstanceBuilder;
import org.genthz.function.DefaultCollectionFiller;
import org.genthz.function.DefaultMapFiller;
import org.genthz.function.Filler;
import org.genthz.function.InstanceBuilder;
import org.genthz.function.InstanceBuilder;
import org.genthz.reflection.GenericUtil;

import javax.script.Bindings;
import javax.script.SimpleBindings;
import java.math.BigDecimal;
import java.math.BigInteger;
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
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.Queue;
import java.util.Random;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.function.Function;

public class DashaDefaults implements Defaults {
    @Override
    public Function<Context, Long> defaultMaxGenerationDepth() {
        return ctx -> 100L;
    }

    private final Filler<?> defaultCollectionFiller = new DefaultCollectionFiller<>();

    private final InstanceBuilder defaultArrayInstanceBuilder = new DefaultArrayInstanceBuilder<>(new GenericUtil(false));

    private final Filler<?> defaultArrayFiller = new DefaultArrayFiller<>();

    private final Filler<? extends Map> defaultMapFiller = new DefaultMapFiller();

    private int defaultCollectionSize = 5;

    private final Random random = new Random();

    @Override
    public int defaultCollectionSize() {
        return this.defaultCollectionSize;
    }

    @Override
    public InstanceBuilder<Boolean> defBooleanInstanceBuilder() {
        return ctx -> this.random.nextBoolean();
    }

    @Override
    public InstanceBuilder<Byte> defByteInstanceBuilder() {
        final byte[] buf = new byte[1];
        this.random.nextBytes(buf);

        return ctx -> buf[0];
    }

    @Override
    public InstanceBuilder<Short> defShortInstanceBuilder() {
        return ctx -> (short) this.random.nextInt();
    }

    @Override
    public InstanceBuilder<Integer> defIntegerInstanceBuilder() {
        return ctx -> this.random.nextInt();
    }

    @Override
    public InstanceBuilder<Long> defLongInstanceBuilder() {
        return ctx -> this.random.nextLong();
    }

    @Override
    public InstanceBuilder<Float> defFloatInstanceBuilder() {
        return ctx -> this.random.nextFloat();
    }

    @Override
    public InstanceBuilder<Double> defDoubleInstanceBuilder() {
        return ctx -> this.random.nextDouble();
    }

    @Override
    public InstanceBuilder<Number> defNumberInstanceBuilder() {
        return ctx -> BigInteger.valueOf(this.random.nextLong());
    }

    @Override
    public InstanceBuilder<BigInteger> defBigIntegerInstanceBuilder() {
        return ctx -> BigInteger.valueOf(this.random.nextLong());
    }

    @Override
    public InstanceBuilder<BigDecimal> defBigDecimalInstanceBuilder() {
        return ctx -> BigDecimal.valueOf(this.random.nextDouble());
    }

    @Override
    public InstanceBuilder<String> defStringInstanceBuilder() {
        return ctx -> RandomStringUtils.randomAlphabetic(10);
    }

    @Override
    public InstanceBuilder<Date> defDateInstanceBuilder() {
        return ctx -> new Date();
    }

    @Override
    public InstanceBuilder<LocalDate> defLocalDateInstanceBuilder() {
        return ctx -> LocalDate.now();
    }

    @Override
    public InstanceBuilder<LocalTime> defLocalTimeInstanceBuilder() {
        return ctx -> LocalTime.now();
    }

    @Override
    public InstanceBuilder<LocalDateTime> defLocalDateTimeInstanceBuilder() {
        return ctx -> LocalDateTime.now();
    }

    @Override
    public InstanceBuilder<OffsetTime> defOffsetTimeInstanceBuilder() {
        return ctx -> OffsetTime.now();
    }

    @Override
    public InstanceBuilder<OffsetDateTime> defOffsetDateTimeInstanceBuilder() {
        return ctx -> OffsetDateTime.now();
    }

    @Override
    public InstanceBuilder<ZonedDateTime> defZonedDateTimeInstanceBuilder() {
        return ctx -> ZonedDateTime.now();
    }

    @Override
    public InstanceBuilder<ZoneId> defZoneIdInstanceBuilder() {
        return ctx -> ZoneId.systemDefault();
    }

    @Override
    public <T extends Collection> InstanceBuilder<T> defCollectionInstanceBuilder() {
        return ctx -> (T) new ArrayList();
    }

    @Override
    public <T extends Collection> Filler<T> defCollectionFiller() {
        return (Filler<T>) this.defaultCollectionFiller;
    }

    @Override
    public <T extends List> InstanceBuilder<T> defListInstanceBuilder() {
        return ctx -> (T) new ArrayList();
    }

    @Override
    public <T extends List> Filler<T> defListFiller() {
        return (Filler<T>) this.defaultCollectionFiller;
    }

    @Override
    public <T extends Queue> InstanceBuilder<T> defQueueInstanceBuilder() {
        return ctx -> (T) new ArrayDeque();
    }

    @Override
    public <T extends Queue> Filler<T> defQueueFiller() {
        return (Filler<T>) this.defaultCollectionFiller;
    }

    @Override
    public <T extends Deque> InstanceBuilder<T> defDequeInstanceBuilder() {
        return ctx -> (T) new ArrayDeque();
    }

    @Override
    public <T extends Deque> Filler<T> defDequeFiller() {
        return (Filler<T>) this.defaultCollectionFiller;
    }

    @Override
    public <T extends Set> InstanceBuilder<T> defSetInstanceBuilder() {
        return ctx -> (T) new HashSet();
    }

    @Override
    public <T extends Set> Filler<T> defSetFiller() {
        return (Filler<T>) this.defaultCollectionFiller;
    }

    @Override
    public <T> InstanceBuilder<T> defArrayInstanceBuilder() {
        return this.defaultArrayInstanceBuilder;
    }

    @Override
    public Filler<?> defArrayFiller() {
        return this.defaultArrayFiller;
    }

    @Override
    public <T extends Map> InstanceBuilder<T> defMapInstanceBuilder() {
        return ctx -> (T) new HashMap();
    }

    @Override
    public <T extends Map> Filler<T> defMapFiller() {
        return (Filler<T>) this.defaultMapFiller;
    }

    @Override
    public <T extends ConcurrentMap> InstanceBuilder<T> defConcurrentMapInstanceBuilder() {
        return ctx -> (T) new ConcurrentHashMap();
    }

    @Override
    public <T extends ConcurrentMap> Filler<T> defConcurrentMapFiller() {
        return (Filler<T>) this.defaultMapFiller;
    }

    @Override
    public <T extends ConcurrentNavigableMap> InstanceBuilder<T> defConcurrentNavigableMapInstanceBuilder() {
        return ctx -> (T) new ConcurrentSkipListMap();
    }

    @Override
    public <T extends ConcurrentNavigableMap> Filler<T> defConcurrentNavigableMapFiller() {
        return (Filler<T>) this.defaultMapFiller;
    }

    @Override
    public <T extends NavigableMap> InstanceBuilder<T> defNavigableMapInstanceBuilder() {
        return ctx -> (T) new TreeMap<>();
    }

    @Override
    public <T extends NavigableMap> Filler<T> defNavigableMapFiller() {
        return (Filler<T>) this.defaultMapFiller;
    }

    @Override
    public <T extends SortedMap> InstanceBuilder<T> defSortedMapInstanceBuilder() {
        return ctx -> (T) new TreeMap<>();
    }

    @Override
    public <T extends SortedMap> Filler<T> defSortedMapFiller() {
        return (Filler<T>) this.defaultMapFiller;
    }
}
