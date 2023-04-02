package org.genthz.dsl.simple;

import org.genthz.dsl.Metric;
import org.genthz.dsl.Selector;

import java.util.Optional;

abstract class AbstractSelector implements Selector {
    private final Optional<Selector> parent;

    private String name = NameGenerator.next();

    private int metric = 1;

    protected AbstractSelector(Selector parent) {
        this.parent = Optional.ofNullable(parent);
    }

    @Override
    public Optional<Selector> up() {
        return this.parent;
    }

    @Override
    public int compareTo(Metric o) {
        return this.metric - o.metric();
    }

    @Override
    public int metric() {
        return this.metric;
    }

    @Override
    public AbstractSelector metric(int mertic) {
        this.metric = mertic;
        return this;
    }

    @Override
    public String name() {
        return this.name;
    }

    @Override
    public AbstractSelector name(CharSequence name) {
        this.name = name != null ?
                name instanceof String
                        ? (String) name
                        : name.toString()
                : NameGenerator.next();

        return this;
    }
}
