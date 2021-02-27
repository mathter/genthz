package org.genthz.configuration.dsl;

import org.genthz.Context;

import java.util.function.Predicate;

public interface Custom {

    public Predicate<Context<?>> predicate();
}
