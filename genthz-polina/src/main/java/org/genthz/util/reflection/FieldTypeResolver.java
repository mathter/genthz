package org.genthz.util.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.Map;

public class FieldTypeResolver extends AbstractTypeResolver {

    private final Field field;

    public FieldTypeResolver(TypeResolver parent, Field field) {
        super(parent);
        this.field = field;
    }

    @Override
    public <T> Class<T> resolve(TypeVariable variable) {
        return null;
    }

    private Map<TypeVariable, Type> map() {
        final Type genericType = this.field.getGenericType();


    }
}
