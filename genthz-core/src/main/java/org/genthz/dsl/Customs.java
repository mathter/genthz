package org.genthz.dsl;

import org.apache.commons.lang3.reflect.TypeUtils;
import org.genthz.context.Context;
import org.genthz.context.InstanceContext;

import java.util.function.Predicate;

public final class Customs {
    private Customs() {
    }

    public static Predicate<Context> isArray() {
        return ctx -> ctx instanceof InstanceContext
                && TypeUtils.isArrayType(((InstanceContext) ctx).type());
    }
}
