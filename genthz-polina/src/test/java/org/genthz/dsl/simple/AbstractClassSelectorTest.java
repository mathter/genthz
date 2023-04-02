package org.genthz.dsl.simple;

public abstract class AbstractClassSelectorTest {
    protected static class S {
        private String stringField;

        private Integer integerField;

        private D<String> dStringField;

        private S0 s0Field;
    }

    protected static class S0 {
        private String stringField;
    }

    protected static class D<T> {
        private T tField;
    }

    protected static class D0<T> extends D<T> {

    }
}
