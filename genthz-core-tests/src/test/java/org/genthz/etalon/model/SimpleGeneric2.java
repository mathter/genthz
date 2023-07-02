package org.genthz.etalon.model;

import java.util.List;
import java.util.Map;

public class SimpleGeneric2<T1, T2> extends Simple {
    private final T1 field1;

    private final T2 field2;

    private final List<T1> field3;

    private final Map<T1, T2> field4;

    public SimpleGeneric2(T1 field1, T2 field2, List<T1> field3, Map<T1, T2> field4) {
        this.field1 = field1;
        this.field2 = field2;
        this.field3 = field3;
        this.field4 = field4;
    }

    public T1 getField1() {
        return field1;
    }

    public T2 getField2() {
        return field2;
    }

    public List<T1> getField3() {
        return field3;
    }

    public Map<T1, T2> getField4() {
        return field4;
    }
}