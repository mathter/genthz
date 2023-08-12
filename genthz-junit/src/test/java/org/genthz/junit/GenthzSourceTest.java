package org.genthz.junit;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.genthz.ObjectFactory;
import org.genthz.ObjectFactoryProvider;
import org.genthz.context.InstanceContext;
import org.genthz.dasha.dsl.DashaDsl;
import org.genthz.function.Filler;
import org.genthz.function.InstanceBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;

public class GenthzSourceTest {
    private static String STRING_VALUE = RandomStringUtils.randomAlphabetic(30);

    private static Integer INTEGER_VALUE = RandomUtils.nextInt();

    @ParameterizedTest
    @GenthzSource
    public void test(String value) {
        Assertions.assertNotNull(value);
    }

    @ParameterizedTest
    @GenthzSource(objectFactoryProvider = TestObjectFactoryProvider.class)
    public void testObjectFactory(String value) {
        Assertions.assertEquals(STRING_VALUE, value);
    }

    @ParameterizedTest
    @GenthzSource
    public void testGenthzParameter(@GenthzParameter(instanceBuilder = TestInstanceBuilder.class, filler = TestFiller.class) Model value) {
        Assertions.assertEquals(STRING_VALUE, value.getStringValue());
        Assertions.assertEquals(INTEGER_VALUE, value.getIntegerValue());
    }

    public static class TestInstanceBuilder implements InstanceBuilder<Model> {
        @Override
        public Model instance(InstanceContext<Model> context) {
            return new Model();
        }
    }

    public static class TestFiller implements Filler<Model> {
        @Override
        public void fill(InstanceContext<Model> context) {
            context.get().stringValue = STRING_VALUE;
            context.get().integerValue = INTEGER_VALUE;
        }
    }

    public static class TestObjectFactoryProvider implements ObjectFactoryProvider {
        @Override
        public ObjectFactory objectFactory() {
            return new DashaDsl() {
                {
                    this.strict(String.class).simple(ctx -> GenthzSourceTest.STRING_VALUE);
                }
            }.objectFactory();
        }
    }

    public static class Model {
        private String stringValue;

        private Integer integerValue;

        public String getStringValue() {
            return stringValue;
        }

        public void setStringValue(String stringValue) {
            this.stringValue = stringValue;
        }

        public Integer getIntegerValue() {
            return integerValue;
        }

        public void setIntegerValue(Integer integerValue) {
            this.integerValue = integerValue;
        }
    }
}
