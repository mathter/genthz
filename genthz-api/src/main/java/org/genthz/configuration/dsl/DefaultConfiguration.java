/*
 * Generated - testing becomes easier
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

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.genthz.GeneratedException;
import org.genthz.InstanceBuilder;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
import java.util.Deque;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.UUID;
import java.util.function.Supplier;

import static org.genthz.configuration.dsl.Selector.METRICS_UNIT;
import static org.genthz.configuration.dsl.Selector.METRICS_ZERO;

/**
 * This is the class of default configuration. There are several defenitions of simple base classes of java.
 * This version of class includes next defenitions:
 * <ul>
 * <li>boolean</li>
 * <li>{@linkplain Boolean}</li>
 * <li>short</li>
 * <li>{@linkplain Short}</li>
 * <li>int</li>
 * <li>{@linkplain Integer}</li>
 * <li>long</li>
 * <li>{@linkplain Long}</li>
 * <li>float</li>
 * <li>{@linkplain Float}</li>
 * <li>double</li>
 * <li>{@linkplain Double}</li>
 * <li>{@linkplain String}</li>
 * <li>{@linkplain UUID}</li>
 * <li>{@linkplain Date}</li>
 * <li>{@linkplain Collection}</li>
 * <li>{@linkplain List}</li>
 * <li>{@linkplain Set}</li>
 * <li>{@linkplain Queue}</li>
 * <li>{@linkplain Deque}</li>
 * <li>Default deep generation breker, see {@linkplain #maxGenerationDeep()}</li>
 * </ul>
 *
 * @author <a href="mailto:mathter@mail.ru">mathter</a>
 * @version 1.0.0
 * @since 1.0.0
 */
public class DefaultConfiguration extends AbstractConfiguration {
    {
        reg(
                // Common types.
                nonstrict(boolean.class).metrics(METRICS_ZERO).instanceBuilder((c) -> RandomUtils.nextBoolean()),
                nonstrict(byte.class).metrics(METRICS_ZERO).instanceBuilder((c) -> RandomUtils.nextBytes(1)[0]),
                nonstrict(short.class).metrics(METRICS_ZERO).instanceBuilder((c) -> (short) RandomUtils.nextInt()),
                nonstrict(int.class).metrics(METRICS_ZERO).instanceBuilder((c) -> RandomUtils.nextInt()),
                nonstrict(long.class).metrics(METRICS_ZERO).instanceBuilder((c) -> RandomUtils.nextLong()),
                nonstrict(float.class).metrics(METRICS_ZERO).instanceBuilder((c) -> RandomUtils.nextFloat()),
                nonstrict(double.class).metrics(METRICS_ZERO).instanceBuilder((c) -> RandomUtils.nextDouble()),

                // Common boxed types.
                nonstrict(Boolean.class).metrics(METRICS_ZERO).instanceBuilder((c) -> RandomUtils.nextBoolean()),
                nonstrict(Byte.class).metrics(METRICS_ZERO).instanceBuilder((c) -> RandomUtils.nextBytes(1)[0]),
                nonstrict(Short.class).metrics(METRICS_ZERO).instanceBuilder((c) -> (short) RandomUtils.nextInt()),
                nonstrict(Integer.class).metrics(METRICS_ZERO).instanceBuilder((c) -> RandomUtils.nextInt()),
                nonstrict(Long.class).metrics(METRICS_ZERO).instanceBuilder((c) -> RandomUtils.nextLong()),
                nonstrict(Float.class).metrics(METRICS_ZERO).instanceBuilder((c) -> RandomUtils.nextFloat()),
                nonstrict(Double.class).metrics(METRICS_ZERO).instanceBuilder((c) -> RandomUtils.nextDouble()),
                nonstrict(String.class).metrics(METRICS_ZERO).instanceBuilder((c) -> RandomStringUtils.randomAlphanumeric(10)),
                nonstrict(UUID.class).metrics(METRICS_ZERO).instanceBuilder((c) -> UUID.randomUUID()),
                nonstrict(Date.class).metrics(METRICS_ZERO).instanceBuilder((c) -> new Date()),
                nonstrict(BigInteger.class).metrics(METRICS_ZERO).instanceBuilder((c)->BigInteger.valueOf(RandomUtils.nextLong())),
                nonstrict(BigDecimal.class).metrics(METRICS_ZERO).instanceBuilder((c)->BigDecimal.valueOf(RandomUtils.nextLong())),

                // Default collections.
                nonstrict(Collection.class)
                        .metrics(METRICS_ZERO)
                        .instanceBuilder(collectionBuilder(defaultCollectionClass())),
                nonstrict(List.class)
                        .metrics(METRICS_UNIT)
                        .instanceBuilder(collectionBuilder(defaultListClass())),
                nonstrict(Set.class)
                        .metrics(METRICS_UNIT)
                        .instanceBuilder(collectionBuilder(defaultSetClass())),
                nonstrict(Queue.class)
                        .metrics(METRICS_UNIT)
                        .instanceBuilder(collectionBuilder(defaultQueueClass())),
                nonstrict(Deque.class)
                        .metrics(METRICS_UNIT)
                        .instanceBuilder(collectionBuilder(defaultDequeClass())),

                // Terminate filler.
                custom((c) -> c.stream().count() > maxGenerationDeep().get())
                        .metrics((c) -> Long.MAX_VALUE / 2)
                        .nonstrict((c, o) -> o, Object.class));
    }

    public DefaultConfiguration() {
        this(DslFactory.dsl());
    }

    public DefaultConfiguration(Dsl dsl) {
        super(dsl);
    }

    private <T extends Collection> InstanceBuilder<T> collectionBuilder(Supplier<Class<? extends T>> supplier) {
        return (c) -> {
            final T collection;
            final Class<?> itemClass = defaultCollectionItemClass().get();

            try {
                collection = supplier.get().newInstance();

                for (int i = 0, count = defaultCollectionSize().get(); i < count; i++) {
                    collection.add(itemClass.newInstance());
                }
            } catch (Exception e) {
                throw new GeneratedException(e);
            }

            return collection;
        };
    }
}
