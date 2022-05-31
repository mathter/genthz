package org.genthz.util.reflection;

import java.lang.reflect.TypeVariable;

public class Test {
    public static void main(String[] args) throws Exception {
        TypeVariable btv = (TypeVariable) B.class.getDeclaredField("f1").getGenericType();
        TypeResolver tr = new FieldTypeResolver(null, A.class.getDeclaredField("b2"));
    }
}
