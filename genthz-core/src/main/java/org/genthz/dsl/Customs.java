package org.genthz.dsl;

import org.apache.commons.lang3.reflect.TypeUtils;
import org.genthz.context.InstanceContext;

import java.util.function.Predicate;

public final class Customs {
    private Customs() {
    }

    public static <T> Predicate<InstanceContext<T>> isArray() {
        return ctx -> ctx instanceof InstanceContext
                && TypeUtils.isArrayType(ctx.type());
    }
}
