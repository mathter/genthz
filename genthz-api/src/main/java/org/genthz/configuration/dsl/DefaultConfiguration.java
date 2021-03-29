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

import java.util.Collection;
import java.util.Date;
import java.util.Deque;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.UUID;
import java.util.function.Supplier;

/**
 * <p>
 * This is the class of default configuration. There are several defenitions of simple base classes of java.
 * This version of class includes next defenitions:
 * <ui>
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
 * </ui>
 * </p>
 *
 * @author <a href="mailto:mathter@mail.ru">mathter</a>
 * @version 1.0.0
 * @since 1.0.0
 */
public class DefaultConfiguration extends AbstractConfiguration {
    {
        reg(
                // Common types.
                nonstrict((c) -> RandomUtils.nextBoolean(), boolean.class),
                nonstrict((c) -> RandomUtils.nextBytes(1)[0], byte.class),
                nonstrict((c) -> (short) RandomUtils.nextInt(), short.class),
                nonstrict((c) -> RandomUtils.nextInt(), int.class),
                nonstrict((c) -> RandomUtils.nextLong(), long.class),
                nonstrict((c) -> RandomUtils.nextFloat(), float.class),
                nonstrict((c) -> RandomUtils.nextDouble(), double.class),

                // Common boxed types.
                nonstrict((c) -> RandomUtils.nextBoolean(), Boolean.class),
                nonstrict((c) -> RandomUtils.nextBytes(1)[0], Byte.class),
                nonstrict((c) -> (short) RandomUtils.nextInt(), Short.class),
                nonstrict((c) -> RandomUtils.nextInt(), Integer.class),
                nonstrict((c) -> RandomUtils.nextLong(), Long.class),
                nonstrict((c) -> RandomUtils.nextFloat(), Float.class),
                nonstrict((c) -> RandomUtils.nextDouble(), Double.class),
                nonstrict((c) -> RandomStringUtils.randomAlphanumeric(10), String.class),
                nonstrict((c) -> UUID.randomUUID(), UUID.class),
                nonstrict((c) -> new Date(), Date.class),

                // Default collections.
                nonstrict(Collection.class)
                        .metrics(Selector.METRICS_UNIT)
                        .instanceBuilder(collectionBuilder(defaultCollectionClass())),
                nonstrict(List.class)
                        .metrics(Selector.METRICS_TWO)
                        .instanceBuilder(collectionBuilder(defaultListClass())),
                nonstrict(Set.class)
                        .metrics(Selector.METRICS_TWO)
                        .instanceBuilder(collectionBuilder(defaultSetClass())),
                nonstrict(Queue.class)
                        .metrics(Selector.METRICS_TWO)
                        .instanceBuilder(collectionBuilder(defaultQueueClass())),
                nonstrict(Deque.class)
                        .metrics(Selector.METRICS_TWO)
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
