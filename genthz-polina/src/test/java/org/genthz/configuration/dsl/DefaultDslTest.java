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
package org.genthz.configuration.dsl;

import org.genthz.ObjectFactory;
import org.genthz.util.Util;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

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

public class DefaultDslTest {

    @ParameterizedTest
    @ValueSource(classes = {boolean.class, byte.class, short.class, int.class, long.class, float.class, double.class,
            Boolean.class, Byte.class, Short.class, Integer.class, Long.class, Float.class, Double.class,
            BigDecimal.class, BigInteger.class,
            String.class, UUID.class,
            Date.class, Instant.class, ZonedDateTime.class, ZoneId.class, LocalDate.class, LocalDateTime.class,
            Collection.class, List.class, Set.class, Queue.class, Deque.class,
            TestEnum.class
    })
    public void testSimple(Class<?> clazz) {
        final ObjectFactory objectFactory = DslFactory.get().dsl().objectFactory();
        final Object result = objectFactory.build(clazz);

        Assertions.assertNotNull(result);
        Assertions.assertTrue((clazz.isPrimitive() ? Util.boxed(clazz) : clazz).isAssignableFrom(result.getClass()));
    }

    public static enum TestEnum {
        A, B, C, D, E
    }
}
