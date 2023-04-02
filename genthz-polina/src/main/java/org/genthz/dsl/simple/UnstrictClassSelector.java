package org.genthz.dsl.simple;

import org.genthz.context.Context;
import org.genthz.dsl.Selector;
import org.genthz.reflection.GenericUtil;

import java.lang.reflect.Type;

class UnstrictClassSelector extends TypeSelector {
    public UnstrictClassSelector(Selector parent, Type type) {
        super(parent, type);
    }

    @Override
    public boolean test(Context<?, ?, ?> context) {
        boolean result;
        final Class<?> actualClass = GenericUtil.rawType(this.type);
        final Type[] actualArgsTypes = GenericUtil.typeParameters(this.type);

        final Class<?> testedClass = GenericUtil.rawType(context.type());
        final Type testedType = context.type();
        final Type[] testedActualArgTypes = GenericUtil.typeParameters(testedType);

        if (actualArgsTypes.length == testedActualArgTypes.length) {
            if (actualArgsTypes.length > 0) {
                result = true;
                for (int i = 0; i < actualArgsTypes.length; i++) {
                    if ((result &= actualArgsTypes[i].equals(testedActualArgTypes[i])) == false) {
                        break;
                    }
                }

                result = actualClass.equals(testedClass);
            } else {
                result = actualClass.isAssignableFrom(testedClass);
            }
        } else {
            result = false;
        }

        return result;
    }
}
