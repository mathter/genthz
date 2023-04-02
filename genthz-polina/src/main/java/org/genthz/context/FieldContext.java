package org.genthz.context;

public interface FieldContext<T, PT, P extends Context<PT, ?, ?> & Instance<PT>> extends ConstructorContext<T, P, String> {
}
