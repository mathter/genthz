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
    public InstanceBuilderConsumer<Boolean> defBooleanInstanceBuilder();

    /**
     * Method returns default instance builder for {@linkplain Byte} or {@linkplain Byte#TYPE} classes.
     *
     * @return instance builder.
     */
    public InstanceBuilderConsumer<Byte> defByteInstanceBuilder();

    /**
     * Method returns default instance builder for {@linkplain Short} or {@linkplain Short#TYPE} classes.
     *
     * @return instance builder.
     */
    public InstanceBuilderConsumer<Short> defShortInstanceBuilder();

    /**
     * Method returns default instance builder for {@linkplain Integer} or {@linkplain Integer#TYPE} classes.
     *
     * @return instance builder.
     */
    public InstanceBuilderConsumer<Integer> defIntegerInstanceBuilder();

    /**
     * Method returns default instance builder for {@linkplain Long} or {@linkplain Long#TYPE} classes.
     *
     * @return instance builder.
     */
    public InstanceBuilderConsumer<Long> defLongInstanceBuilder();

    /**
     * Method returns default instance builder for {@linkplain Float} or {@linkplain Float#TYPE} classes.
     *
     * @return instance builder.
     */
    public InstanceBuilderConsumer<Float> defFloatInstanceBuilder();

    /**
     * Method returns default instance builder for {@linkplain Double} or {@linkplain Double#TYPE} classes.
     *
     * @return instance builder.
     */
    public InstanceBuilderConsumer<Double> defDoubleInstanceBuilder();

    /**
     * Method returns default instance builder for {@linkplain String}.
     *
     * @return instance builder.
     */
    public InstanceBuilderConsumer<String> defStringInstanceBuilder();

    /**
     * Method returns default instance builder for {@linkplain Date}.
     *
     * @return instance builder.
     */
    public InstanceBuilderConsumer<Date> defDateInstanceBuilder();

    /**
     * Method returns default instance builder for {@linkplain LocalDate}.
     *
     * @return instance builder.
     */
    public InstanceBuilderConsumer<LocalDate> defLocalDateInstanceBuilder();

    /**
     * Method returns default instance builder for {@linkplain LocalTime}.
     *
     * @return instance builder.
     */
    public InstanceBuilderConsumer<LocalTime> defLocalTimeInstanceBuilder();

    /**
     * Method returns default instance builder for {@linkplain LocalDateTime}.
     *
     * @return instance builder.
     */
    public InstanceBuilderConsumer<LocalDateTime> defLocalDateTimeInstanceBuilder();

    /**
     * Method returns default instance builder for {@linkplain OffsetTime}.
     *
     * @return instance builder.
     */
    public InstanceBuilderConsumer<OffsetTime> defOffsetTimeInstanceBuilder();

    /**
     * Method returns default instance builder for {@linkplain OffsetDateTime}.
     *
     * @return instance builder.
     */
    public InstanceBuilderConsumer<OffsetDateTime> defOffsetDateTimeInstanceBuilder();

    /**
     * Method returns default instance builder for {@linkplain ZonedDateTime}.
     *
     * @return instance builder.
     */
    public InstanceBuilderConsumer<ZonedDateTime> defZonedDateTimeInstanceBuilder();

    /**
     * Method returns default instance builder for {@linkplain ZoneId}.
     *
     * @return instance builder.
     */
    public InstanceBuilderConsumer<ZoneId> defZoneIdInstanceBuilder();

    /**
     * Method returns default instance builder for {@linkplain Collection}.
     *
     * @return instance builder.
     */
    public InstanceBuilderConsumer<Collection> defCollectionInstanceBuilder();

    /**
     * Method returns default filler for {@linkplain Collection}.
     *
     * @return filler.
     */
    public Filler<Collection> defCollectionFiller();

    /**
     * Method returns default instance builder for {@linkplain List}.
     *
     * @return instance builder.
     */
    public InstanceBuilderConsumer<List> defListInstanceBuilder();

    /**
     * Method returns default filler for {@linkplain List}.
     *
     * @return filler.
     */
    public Filler<List> defListFiller();

    /**
     * Method returns default instance builder for {@linkplain Queue}.
     *
     * @return instance builder.
     */
    public InstanceBuilderConsumer<Queue> defQueueInstanceBuilder();

    /**
     * Method returns default filler for {@linkplain Queue}.
     *
     * @return filler.
     */
    public Filler<Queue> defQueueFiller();

    /**
     * Method returns default instance builder for {@linkplain Deque}.
     *
     * @return instance builder.
     */
    public InstanceBuilderConsumer<Deque> defDequeInstanceBuilder();

    /**
     * Method returns default filler for {@linkplain Deque}.
     *
     * @return filler.
     */
    public Filler<Deque> defDequeFiller();

    /**
     * Method returns default instance builder for {@linkplain Set}.
     *
     * @return instance builder.
     */
    public InstanceBuilderConsumer<Set> defSetInstanceBuilder();

    /**
     * Method returns default filler for {@linkplain Set}.
     *
     * @return filler.
     */
    public Filler<Set> defSetFiller();

    /**
     * Method returns default instance builder for java arrays.
     *
     * @return instance builder.
     */
    public <T> InstanceBuilderConsumer<T> defArrayInstanceBuilder();

    /**
     * Method returns default filler for java arrays.
     *
     * @return filler.
     */
    public Filler<?> defArrayFiller();
}
