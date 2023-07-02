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
