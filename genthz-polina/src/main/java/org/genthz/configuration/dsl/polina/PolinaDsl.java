/*
 * GenThz - testing becomes easier
 *
 * Copyright (C) 2020 mathter@mail.ru
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
package org.genthz.configuration.dsl.polina;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.genthz.configuration.dsl.Dsl;
import org.genthz.configuration.dsl.Selector;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.Deque;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.UUID;

class PolinaDsl extends BaseDsl {
    {
        strict(boolean.class).metrics(Selector.METRICS_ZERO).ib(c -> RandomUtils.nextBoolean());
        strict(byte.class).metrics(Selector.METRICS_ZERO).ib(c -> RandomUtils.nextBytes(1)[0]);
        strict(short.class).metrics(Selector.METRICS_ZERO).ib(c -> (short) RandomUtils.nextInt());
        strict(int.class).metrics(Selector.METRICS_ZERO).ib(c -> RandomUtils.nextInt());
        strict(long.class).metrics(Selector.METRICS_ZERO).ib(c -> RandomUtils.nextLong());
        strict(float.class).metrics(Selector.METRICS_ZERO).ib(c -> RandomUtils.nextFloat());
        strict(double.class).metrics(Selector.METRICS_ZERO).ib(c -> RandomUtils.nextDouble());

        strict(Boolean.class).metrics(Selector.METRICS_ZERO).ib(c -> RandomUtils.nextBoolean());
        strict(Byte.class).metrics(Selector.METRICS_ZERO).ib(c -> RandomUtils.nextBytes(1)[0]);
        strict(Short.class).metrics(Selector.METRICS_ZERO).ib(c -> (short) RandomUtils.nextInt());
        strict(Integer.class).metrics(Selector.METRICS_ZERO).ib(c -> RandomUtils.nextInt());
        strict(Long.class).metrics(Selector.METRICS_ZERO).ib(c -> RandomUtils.nextLong());
        strict(Float.class).metrics(Selector.METRICS_ZERO).ib(c -> RandomUtils.nextFloat());
        strict(Double.class).metrics(Selector.METRICS_ZERO).ib(c -> RandomUtils.nextDouble());

        strict(BigDecimal.class).metrics(Selector.METRICS_ZERO).ib(c -> BigDecimal.valueOf(RandomUtils.nextLong()));
        strict(BigInteger.class).metrics(Selector.METRICS_ZERO).ib(c -> BigInteger.valueOf(RandomUtils.nextLong()));

        strict(String.class).metrics(Selector.METRICS_ZERO).ib(c -> RandomStringUtils.randomAlphabetic(10));
        strict(UUID.class).metrics(Selector.METRICS_ZERO).ib(c -> UUID.randomUUID());

        strict(Date.class).metrics(Selector.METRICS_ZERO).ib(c -> new Date());
        strict(Instant.class).metrics(Selector.METRICS_ZERO).ib(c -> Instant.now());
        strict(ZonedDateTime.class).metrics(Selector.METRICS_ZERO).ib(c -> ZonedDateTime.now());
        strict(ZoneId.class).metrics(Selector.METRICS_ZERO).ib(c -> ZoneId.systemDefault());
        strict(LocalDate.class).metrics(Selector.METRICS_ZERO).ib(c -> LocalDate.now());
        strict(LocalDateTime.class).metrics(Selector.METRICS_ZERO).ib(c -> LocalDateTime.now());

        unstrict(Collection.class).metrics(Selector.METRICS_ZERO).ib(defaults().defaultCollectionInstanceBuilder());
        unstrict(List.class).metrics(Selector.METRICS_ONE).ib(defaults().defaultListInstanceBuilder());
        unstrict(Set.class).metrics(Selector.METRICS_ONE).ib(defaults().defaultSetInstanceBuilder());
        unstrict(Queue.class).metrics(Selector.METRICS_ONE).ib(defaults().defaultQueueInstanceBuilder());
        unstrict(Deque.class).metrics(Selector.METRICS_TWO).ib(defaults().defaultDequeInstanceBuilder());
    }
}
