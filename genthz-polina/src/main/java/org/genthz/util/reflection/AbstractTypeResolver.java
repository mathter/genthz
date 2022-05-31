package org.genthz.util.reflection;

import java.lang.reflect.TypeVariable;

public abstract class AbstractTypeResolver implements TypeResolver {
    private static final TypeResolver NULL = new TypeResolver() {
        @Override
        public <T> Class<T> resolve(TypeVariable variable) {
            return (Class<T>) Object.class;
        }
    };

    protected final TypeResolver parent;

    public AbstractTypeResolver(TypeResolver parent) {
        this.parent = parent != null ? parent : NULL;
    }
}
