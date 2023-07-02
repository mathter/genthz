package org.genthz.etalon;

import org.apache.commons.lang3.reflect.TypeUtils;
import org.genthz.etalon.model.SimpleGeneric2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public abstract class EtalonConstructorObjectFactoryTest extends AbstractEtalonObjectFactoryTest {
    @ParameterizedTest
    @DisplayName("Test of constructor argument based generation")
    @MethodSource("data")
    public void test(Type t1, Type t2) {
        final SimpleGeneric2<?, ?> value = this.objectFactory().get(SimpleGeneric2.class, t1, t2);

        System.out.println(value);
    }

    private static Stream<Arguments> data() {
        return Stream.of(
                Arguments.of(String.class, String.class),
                Arguments.of(TypeUtils.parameterize(List.class, String.class), String.class),
                Arguments.of(TypeUtils.parameterize(Map.class, String.class, int.class), String.class)
        );
    }
}
