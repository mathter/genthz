package org.genthz.util.reflection;

import java.lang.reflect.TypeVariable;

public interface TypeResolver {
    public <T> Class<T> resolve(TypeVariable variable);
}
