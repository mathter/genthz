package org.genthz.summer;

import org.genthz.configuration.dsl.Dsl;

public class DslFactory extends org.genthz.configuration.dsl.DslFactory {
    private static final String ID = "summer";

    @Override
    protected String id() {
        return DslFactory.ID;
    }

    @Override
    protected Dsl newDsl() {
        return null;
    }
}
