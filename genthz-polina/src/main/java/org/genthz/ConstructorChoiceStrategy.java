package org.genthz;

import java.lang.reflect.Constructor;
import java.lang.reflect.Type;

public interface ConstructorChoiceStrategy {
    public <T> Constructor<T> constructor(Type type);
}
