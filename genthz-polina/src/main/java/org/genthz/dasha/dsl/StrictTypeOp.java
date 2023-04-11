package org.genthz.dasha.dsl;

import org.genthz.context.Context;
import org.genthz.dsl.Customable;
import org.genthz.dsl.FillerFirst;
import org.genthz.dsl.InstanceBuilderFirst;
import org.genthz.dsl.Pathable;
import org.genthz.function.Selector;
import org.genthz.dsl.Strictable;
import org.genthz.dsl.Unstricable;

import java.lang.reflect.Type;
import java.util.function.Predicate;

class StrictTypeOp extends TypeOp implements Pathable, Customable {
    public StrictTypeOp(Op up, Type type, Type... genericTypeArgs) {
        super(up, type, genericTypeArgs);
    }

    @Override
    public Selector selector() {
        return new StrictClassSelector(this.up().map(Op::selector).orElse(null), this.type);
    }

    @Override
    public <S extends Pathable & Strictable & Unstricable & InstanceBuilderFirst & FillerFirst> S custom(Predicate<Context> predicate) {
        return (S) new CustomOps(this, predicate);
    }

    @Override
    public <S extends Pathable & Strictable & Unstricable & Customable & InstanceBuilderFirst & FillerFirst> S path(String path) {
        return (S) new PathOp(this, path);
    }
}
