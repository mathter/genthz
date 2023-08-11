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
package org.genthz.etalon;

import org.genthz.ObjectFactory;
import org.genthz.dasha.dsl.DashaDsl;
import org.genthz.dsl.Dsl;
import org.genthz.etalon.model.SimpleGeneric2;
import org.genthz.etalon.model.SimpleTestModel;
import org.genthz.reflection.reference.GetMethodReference;
import org.genthz.reflection.reference.SetMethodReference;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.lang.reflect.Type;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Stream;

public abstract class EtalonPathObjectFactoryTest extends AbstractEtalonObjectFactoryTest {
    @ParameterizedTest
    @DisplayName("Test paths")
    @MethodSource("data")
    public void test(Dsl dsl, Consumer assertion, Class clazz, Type... typeArguments) {
        final ObjectFactory objectFactory = this.objectFactory(dsl);
        final Object value = objectFactory.get(clazz, typeArguments);

        assertion.accept(value);
    }

    private static Stream<Arguments> data() {
        return Stream.of(
                Arguments.of(
                        Optional.of(new DashaDsl().def())
                                .map(e -> {
                                    e.path((GetMethodReference<SimpleTestModel, String>) SimpleTestModel::getName)
                                            .simple(ctx -> "Name");
                                    return e;
                                })
                                .get(),
                        (Consumer<SimpleTestModel>) e -> {
                            Assertions.assertNotNull(e);
                            Assertions.assertEquals("Name", e.getName());
                        },
                        SimpleTestModel.class,
                        null
                ),
                Arguments.of(
                        Optional.of(new DashaDsl().def())
                                .map(e -> {
                                    e.path((SetMethodReference<SimpleTestModel, String>) SimpleTestModel::setName)
                                            .simple(ctx -> "Name");
                                    return e;
                                })
                                .get(),
                        (Consumer<SimpleTestModel>) e -> {
                            Assertions.assertNotNull(e);
                            Assertions.assertEquals("Name", e.getName());
                        },
                        SimpleTestModel.class,
                        null
                ),
                Arguments.of(
                        Optional.of(new DashaDsl().def())
                                .map(e -> {
                                    e.path("name")
                                            .simple(ctx -> "Name");
                                    return e;
                                })
                                .get(),
                        (Consumer<SimpleTestModel>) e -> {
                            Assertions.assertNotNull(e);
                            Assertions.assertEquals("Name", e.getName());
                        },
                        SimpleTestModel.class,
                        null
                ),
                Arguments.of(
                        Optional.of(new DashaDsl().def())
                                .map(e -> {
                                    e.path("name")
                                            .strict(String.class)
                                            .simple(ctx -> "Name");
                                    return e;
                                })
                                .get(),
                        (Consumer<SimpleTestModel>) e -> {
                            Assertions.assertNotNull(e);
                            Assertions.assertEquals("Name", e.getName());
                        },
                        SimpleTestModel.class,
                        null
                ),
                Arguments.of(
                        Optional.of(new DashaDsl().def())
                                .map(e -> {
                                    e.strict(SimpleTestModel.class)
                                            .path("name")
                                            .simple(ctx -> "Name");
                                    return e;
                                })
                                .get(),
                        (Consumer<SimpleTestModel>) e -> {
                            Assertions.assertNotNull(e);
                            Assertions.assertEquals("Name", e.getName());
                        },
                        SimpleTestModel.class,
                        null
                ),
                Arguments.of(
                        Optional.of(new DashaDsl().def())
                                .map(e -> {
                                    e.path("otherNames[1]")
                                            .simple(ctx -> "Name");
                                    return e;
                                })
                                .get(),
                        (Consumer<SimpleTestModel>) e -> {
                            Assertions.assertNotNull(e);
                            Assertions.assertEquals("Name", e.getOtherNames().get(1));
                        },
                        SimpleTestModel.class,
                        null
                ),
                Arguments.of(
                        new DashaDsl() {
                            {
                                path("c{0}").simple(ctx -> "Name");
                            }
                        }.def(),
                        (Consumer<SimpleGeneric2>) e -> {
                            Assertions.assertNotNull(e);
                            Assertions.assertEquals("Name", e.getField1());
                        },
                        SimpleGeneric2.class,
                        new Type[]{String.class, Integer.class}
                )
        );
    }
}
