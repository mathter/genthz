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
package org.genthz;

import org.genthz.function.Filler;
import org.genthz.function.InstanceBuilder;

import java.math.BigDecimal;
import java.math.BigInteger;
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
import java.util.Map;
import java.util.NavigableMap;
import java.util.Queue;
import java.util.Set;
import java.util.SortedMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentNavigableMap;

/**
 * This class represents same information for generation class instances, for
 * example default size of collections or instance builders and fillers and etc.
 *
 * @author mathter
 * @since 3.0.0
 */
public interface Defaults {
    /**
     * Method returns default size of collections or arrays which will be generated.
     *
     * @return default size of collections or arrays.
     */
    public int defaultCollectionSize();

    /**
     * Method returns default instance builder for {@linkplain Boolean} or {@linkplain Boolean#TYPE} classes.
     *
     * @return instance builder.
     */
    public InstanceBuilder<Boolean> defBooleanInstanceBuilder();

    /**
     * Method returns default instance builder for {@linkplain Byte} or {@linkplain Byte#TYPE} classes.
     *
     * @return instance builder.
     */
    public InstanceBuilder<Byte> defByteInstanceBuilder();

    /**
     * Method returns default instance builder for {@linkplain Short} or {@linkplain Short#TYPE} classes.
     *
     * @return instance builder.
     */
    public InstanceBuilder<Short> defShortInstanceBuilder();

    /**
     * Method returns default instance builder for {@linkplain Integer} or {@linkplain Integer#TYPE} classes.
     *
     * @return instance builder.
     */
    public InstanceBuilder<Integer> defIntegerInstanceBuilder();

    /**
     * Method returns default instance builder for {@linkplain Long} or {@linkplain Long#TYPE} classes.
     *
     * @return instance builder.
     */
    public InstanceBuilder<Long> defLongInstanceBuilder();

    /**
     * Method returns default instance builder for {@linkplain Float} or {@linkplain Float#TYPE} classes.
     *
     * @return instance builder.
     */
    public InstanceBuilder<Float> defFloatInstanceBuilder();

    /**
     * Method returns default instance builder for {@linkplain Double} or {@linkplain Double#TYPE} classes.
     *
     * @return instance builder.
     */
    public InstanceBuilder<Double> defDoubleInstanceBuilder();

    public InstanceBuilder<Number> defNumberInstanceBuilder();

    public InstanceBuilder<BigInteger> defBigIntegerInstanceBuilder();

    public InstanceBuilder<BigDecimal> defBigDecimalInstanceBuilder();

    /**
     * Method returns default instance builder for {@linkplain String}.
     *
     * @return instance builder.
     */
    public InstanceBuilder<String> defStringInstanceBuilder();

    /**
     * Method returns default instance builder for {@linkplain Date}.
     *
     * @return instance builder.
     */
    public InstanceBuilder<Date> defDateInstanceBuilder();

    /**
     * Method returns default instance builder for {@linkplain LocalDate}.
     *
     * @return instance builder.
     */
    public InstanceBuilder<LocalDate> defLocalDateInstanceBuilder();

    /**
     * Method returns default instance builder for {@linkplain LocalTime}.
     *
     * @return instance builder.
     */
    public InstanceBuilder<LocalTime> defLocalTimeInstanceBuilder();

    /**
     * Method returns default instance builder for {@linkplain LocalDateTime}.
     *
     * @return instance builder.
     */
    public InstanceBuilder<LocalDateTime> defLocalDateTimeInstanceBuilder();

    /**
     * Method returns default instance builder for {@linkplain OffsetTime}.
     *
     * @return instance builder.
     */
    public InstanceBuilder<OffsetTime> defOffsetTimeInstanceBuilder();

    /**
     * Method returns default instance builder for {@linkplain OffsetDateTime}.
     *
     * @return instance builder.
     */
    public InstanceBuilder<OffsetDateTime> defOffsetDateTimeInstanceBuilder();

    /**
     * Method returns default instance builder for {@linkplain ZonedDateTime}.
     *
     * @return instance builder.
     */
    public InstanceBuilder<ZonedDateTime> defZonedDateTimeInstanceBuilder();

    /**
     * Method returns default instance builder for {@linkplain ZoneId}.
     *
     * @return instance builder.
     */
    public InstanceBuilder<ZoneId> defZoneIdInstanceBuilder();

    /**
     * Method returns default instance builder for {@linkplain Collection}.
     *
     * @return instance builder.
     */
    public <T extends Collection> InstanceBuilder<T> defCollectionInstanceBuilder();

    /**
     * Method returns default filler for {@linkplain Collection}.
     *
     * @return filler.
     */
    public <T extends Collection> Filler<T> defCollectionFiller();

    /**
     * Method returns default instance builder for {@linkplain List}.
     *
     * @return instance builder.
     */
    public <T extends List> InstanceBuilder<T> defListInstanceBuilder();

    /**
     * Method returns default filler for {@linkplain List}.
     *
     * @return filler.
     */
    public <T extends List> Filler<T> defListFiller();

    /**
     * Method returns default instance builder for {@linkplain Queue}.
     *
     * @return instance builder.
     */
    public <T extends Queue> InstanceBuilder<T> defQueueInstanceBuilder();

    /**
     * Method returns default filler for {@linkplain Queue}.
     *
     * @return filler.
     */
    public <T extends Queue> Filler<T> defQueueFiller();

    /**
     * Method returns default instance builder for {@linkplain Deque}.
     *
     * @return instance builder.
     */
    public <T extends Deque> InstanceBuilder<T> defDequeInstanceBuilder();

    /**
     * Method returns default filler for {@linkplain Deque}.
     *
     * @return filler.
     */
    public <T extends Deque> Filler<T> defDequeFiller();

    /**
     * Method returns default instance builder for {@linkplain Set}.
     *
     * @return instance builder.
     */
    public <T extends Set> InstanceBuilder<T> defSetInstanceBuilder();

    /**
     * Method returns default filler for {@linkplain Set}.
     *
     * @return filler.
     */
    public <T extends Set> Filler<T> defSetFiller();

    /**
     * Method returns default instance builder for java arrays.
     *
     * @return instance builder.
     */
    public <T> InstanceBuilder<T> defArrayInstanceBuilder();

    /**
     * Method returns default filler for java arrays.
     *
     * @return filler.
     */
    public <T> Filler defArrayFiller();

    /**
     * Method returns default instance builder for {@linkplain Map}.
     *
     * @return instance builder.
     */
    public <T extends Map> InstanceBuilder<T> defMapInstanceBuilder();

    /**
     * Method returns default filler for {@linkplain Map}.
     *
     * @return filler.
     */
    public <T extends Map> Filler<T> defMapFiller();

    /**
     * Method returns default instance builder for {@linkplain ConcurrentMap}.
     *
     * @return instance builder.
     */
    public <T extends ConcurrentMap> InstanceBuilder<T> defConcurrentMapInstanceBuilder();

    /**
     * Method returns default filler for {@linkplain ConcurrentMap}.
     *
     * @return filler.
     */
    public <T extends ConcurrentMap> Filler<T> defConcurrentMapFiller();

    /**
     * Method returns default instance builder for {@linkplain ConcurrentNavigableMap}.
     *
     * @return instance builder.
     */
    public <T extends ConcurrentNavigableMap> InstanceBuilder<T> defConcurrentNavigableMapInstanceBuilder();

    /**
     * Method returns default filler for {@linkplain ConcurrentNavigableMap}.
     *
     * @return filler.
     */
    public <T extends ConcurrentNavigableMap> Filler<T> defConcurrentNavigableMapFiller();

    /**
     * Method returns default instance builder for {@linkplain NavigableMap}.
     *
     * @return instance builder.
     */
    public <T extends NavigableMap> InstanceBuilder<T> defNavigableMapInstanceBuilder();

    /**
     * Method returns default filler for {@linkplain NavigableMap}.
     *
     * @return filler.
     */
    public <T extends NavigableMap> Filler<T> defNavigableMapFiller();

    /**
     * Method returns default instance builder for {@linkplain SortedMap}.
     *
     * @return instance builder.
     */
    public <T extends SortedMap> InstanceBuilder<T> defSortedMapInstanceBuilder();

    /**
     * Method returns default filler for {@linkplain SortedMap}.
     *
     * @return filler.
     */
    public <T extends SortedMap> Filler<T> defSortedMapFiller();
}
