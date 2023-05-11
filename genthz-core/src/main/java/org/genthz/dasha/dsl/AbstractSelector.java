package org.genthz.dasha.dsl;

import org.genthz.dsl.Metric;
import org.genthz.function.Selector;

import java.util.Optional;

abstract class AbstractSelector implements Selector {
    private final Optional<Selector> up;

    private String name = NameGenerator.next();

    private int metric = 1;

    protected AbstractSelector(Selector up) {
        this.up = Optional.ofNullable(up);
    }

    @Override
    public Optional<Selector> up() {
        return this.up;
    }

    @Override
    public int compareTo(Metric o) {
        return this.effective() - o.effective();
    }

    @Override
    public int metric() {
        return this.metric;
    }

    @Override
    public int effective() {
        return this.metric + this.up.map(e -> e.effective()).orElse(0);
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
