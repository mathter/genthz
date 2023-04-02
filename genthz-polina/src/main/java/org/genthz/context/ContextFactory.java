package org.genthz.context;

import org.genthz.context.Bindings;
import org.genthz.context.ConstructorContext;
import org.genthz.context.ObjectContext;

import java.lang.reflect.Type;
import java.util.Collection;

public interface ContextFactory {
    default public <T> ObjectContext<T, ?> contexts(Class<T> clazz, Type... genericTypes) {
        return this.contexts(null, clazz, genericTypes);
    }

    public <T> ObjectContext<T, ?> contexts(Bindings bindings, Class<T> clazz, Type... genericTypes);

    public <T> Collection<ConstructorContext<?, ?, ?>> contexts(ConstructorContext<T, ?, ?> context);
}
