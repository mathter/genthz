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

import org.genthz.FieldMatchers;
import org.genthz.etalon.model.Simple;
import org.genthz.etalon.model.SimpleGeneric;
import org.genthz.function.FieldMatcher;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.lang.reflect.TypeVariable;
import java.util.Collections;
import java.util.stream.Stream;

public class EtalonFieldMatchersTest {
    @ParameterizedTest
    @MethodSource("data")
    public void test(FieldMatcher matcher, FieldMatcher.Context context, boolean isMatched) {
        Assertions.assertEquals(isMatched, matcher.matche(context));
    }

    public static Stream<Arguments> data() throws Exception {
        return Stream.of(
                Arguments.of(
                        FieldMatchers.declaring(null).type(null).name("stringField"),
                        FieldMatcher.of(Simple.class.getDeclaredField("stringField")),
                        true
                ),
                Arguments.of(
                        FieldMatchers.declaring(null).type(null).name("stringField"),
                        FieldMatcher.of(Simple.class.getDeclaredField("dateField")),
                        false
                ),
                Arguments.of(
                        FieldMatchers.declaring(null).type(null).name("dateField"),
                        null,
                        false
                ),
                Arguments.of(
                        FieldMatchers.declaring(SimpleGeneric.class).type(null).name("tField"),
                        FieldMatcher.of(SimpleGeneric.class.getDeclaredField("tField")),
                        true
                ),
                Arguments.of(
                        FieldMatchers.declaring(SimpleGeneric.class).type(Object.class).name("tField"),
                        FieldMatcher.of(SimpleGeneric.class.getDeclaredField("tField")),
                        true
                ),
                Arguments.of(
                        FieldMatchers.name(SimpleGeneric<Object, Object>::gettField),
                        FieldMatcher.of(SimpleGeneric.class.getDeclaredField("tField")),
                        true
                ),
                Arguments.of(
                        FieldMatchers.name(SimpleGeneric<String, Integer>::gettField),
                        FieldMatcher.of(
                                SimpleGeneric.class.getDeclaredField("tField"),
                                Collections.singletonMap(
                                        (TypeVariable<?>) SimpleGeneric.class.getDeclaredField("tField").getGenericType(),
                                        Integer.class
                                )
                        ),
                        true
                ),
                Arguments.of(
                        FieldMatchers.name(A::getSg),
                        FieldMatcher.of(A.class.getDeclaredField("sg")),
                        true
                )
        );
    }

    public static class A {
        private SimpleGeneric<String, Integer> sg;

        public SimpleGeneric<String, Integer> getSg() {
            return sg;
        }

        public void setSg(SimpleGeneric<String, Integer> sg) {
            this.sg = sg;
        }
    }
}
