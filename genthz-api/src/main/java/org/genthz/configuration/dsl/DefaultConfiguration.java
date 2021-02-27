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

import java.util.Collection;
import java.util.Date;
import java.util.UUID;

public class DefaultConfiguration extends AbstractConfiguration {
    {
        reg(
                nonstrict((c) -> RandomUtils.nextBoolean(), boolean.class),
                nonstrict((c) -> RandomUtils.nextBytes(1)[0], byte.class),
                nonstrict((c) -> (short) RandomUtils.nextInt(), short.class),
                nonstrict((c) -> RandomUtils.nextInt(), int.class),
                nonstrict((c) -> RandomUtils.nextLong(), long.class),
                nonstrict((c) -> RandomUtils.nextFloat(), float.class),
                nonstrict((c) -> RandomUtils.nextDouble(), double.class),
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
                nonstrict((c) -> {
                    final Collection collection;
                    final Class<?> itemClass = defaultCollectionItemClass().get();

                    try {
                        collection = defaultCollectionClass().get().newInstance();

                        for (int i = 0, count = defaultCollectionSize().get(); i < count; i++) {
                            collection.add(itemClass.newInstance());
                        }
                    } catch (Exception e) {
                        throw new GeneratedException(e);
                    }

                    return collection;
                }, Collection.class),
                custom((c) -> c.stream().count() > maxGenerationDeep().get())
                        .metrics((c) -> Long.MAX_VALUE)
                        .nonstrict((c, o) -> o, Object.class));
    }

    public DefaultConfiguration() {
        this(DslFactory.dsl());
    }

    public DefaultConfiguration(Dsl dsl) {
        super(dsl);
    }
}
