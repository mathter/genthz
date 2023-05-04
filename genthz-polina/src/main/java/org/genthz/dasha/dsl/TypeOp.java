package org.genthz.dasha.dsl;

import java.lang.reflect.Type;

abstract class TypeOp extends SelectorOp {
    protected final Type type;

    protected final Type[] genericTypeArgs;

    public TypeOp(SelectorOp up, Type type, Type... genericTypeArgs) {
        super(up);
        this.type = type;
        this.genericTypeArgs = genericTypeArgs;
    }
}
