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
import org.genthz.configuration.dsl.InstanceBuilders;
import org.genthz.configuration.dsl.function.EnumInstanceBuilder;
import org.genthz.context.context.Context;

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
import java.util.function.Function;

class PolinaDsl extends BaseDsl {
    {
        final Function<Context<?>, Integer> objectClassMetrics = ctx -> this.defaults().objectClassMetrics();
        final Function<Context<?>, Integer> simpleClassMetrics = objectClassMetrics.andThen(i -> i + 1);
        final Function<Context<?>, Integer> collectionClassMetrics = objectClassMetrics.andThen(i -> i + 1);
        final Function<Context<?>, Integer> subCollectionClassMetrics = collectionClassMetrics.andThen(i -> i + 1);
        final Function<Context<?>, Integer> subSubCollectionClassMetrics = collectionClassMetrics.andThen(i -> i + 1);

        strict(boolean.class).metrics(simpleClassMetrics).ib(c -> RandomUtils.nextBoolean());
        strict(byte.class).metrics(simpleClassMetrics).ib(c -> RandomUtils.nextBytes(1)[0]);
        strict(short.class).metrics(simpleClassMetrics).ib(c -> (short) RandomUtils.nextInt());
        strict(int.class).metrics(simpleClassMetrics).ib(c -> RandomUtils.nextInt());
        strict(long.class).metrics(simpleClassMetrics).ib(c -> RandomUtils.nextLong());
        strict(float.class).metrics(simpleClassMetrics).ib(c -> RandomUtils.nextFloat());
        strict(double.class).metrics(simpleClassMetrics).ib(c -> RandomUtils.nextDouble());

        strict(Boolean.class).metrics(simpleClassMetrics).ib(c -> RandomUtils.nextBoolean());
        strict(Byte.class).metrics(simpleClassMetrics).ib(c -> RandomUtils.nextBytes(1)[0]);
        strict(Short.class).metrics(simpleClassMetrics).ib(c -> (short) RandomUtils.nextInt());
        strict(Integer.class).metrics(simpleClassMetrics).ib(c -> RandomUtils.nextInt());
        strict(Long.class).metrics(simpleClassMetrics).ib(c -> RandomUtils.nextLong());
        strict(Float.class).metrics(simpleClassMetrics).ib(c -> RandomUtils.nextFloat());
        strict(Double.class).metrics(simpleClassMetrics).ib(c -> RandomUtils.nextDouble());

        strict(BigDecimal.class).metrics(simpleClassMetrics).ib(c -> BigDecimal.valueOf(RandomUtils.nextLong()));
        strict(BigInteger.class).metrics(simpleClassMetrics).ib(c -> BigInteger.valueOf(RandomUtils.nextLong()));

        strict(String.class).metrics(simpleClassMetrics).ib(c -> RandomStringUtils.randomAlphabetic(10));
        strict(UUID.class).metrics(simpleClassMetrics).ib(c -> UUID.randomUUID());

        strict(Date.class).metrics(simpleClassMetrics).ib(c -> new Date());
        strict(Instant.class).metrics(simpleClassMetrics).ib(c -> Instant.now());
        strict(ZonedDateTime.class).metrics(simpleClassMetrics).ib(c -> ZonedDateTime.now());
        strict(ZoneId.class).metrics(simpleClassMetrics).ib(c -> ZoneId.systemDefault());
        strict(LocalDate.class).metrics(simpleClassMetrics).ib(c -> LocalDate.now());
        strict(LocalDateTime.class).metrics(simpleClassMetrics).ib(c -> LocalDateTime.now());

        unstrict(Collection.class).metrics(collectionClassMetrics).ib(defaults().defaultCollectionInstanceBuilder());
        unstrict(List.class).metrics(subCollectionClassMetrics).ib(defaults().defaultListInstanceBuilder());
        unstrict(Set.class).metrics(subCollectionClassMetrics).ib(defaults().defaultSetInstanceBuilder());
        unstrict(Queue.class).metrics(subCollectionClassMetrics).ib(defaults().defaultQueueInstanceBuilder());
        unstrict(Deque.class).metrics(subSubCollectionClassMetrics).ib(defaults().defaultDequeInstanceBuilder());

        unstrict(Enum.class).metrics(simpleClassMetrics).ib(new EnumInstanceBuilder<>());
        unstrict(Object.class)
                .metrics(objectClassMetrics)
                .ib(InstanceBuilders.minArgCount())
                .f(new DefaultFiller<>(this.defaults()));
    }
}
