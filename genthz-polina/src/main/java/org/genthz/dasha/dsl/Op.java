package org.genthz.dasha.dsl;

import org.genthz.dsl.Metric;
import org.genthz.function.Selector;
import org.genthz.util.StreamUtil;

import java.util.Optional;
import java.util.stream.Stream;

abstract class Op implements Metric<Op> {
    private final Optional<Op> up;

    private int metric;

    public Op(Op up) {
        this.up = Optional.ofNullable(up);
    }

    @Override
    public int metric() {
        return this.metric;
    }

    @Override
    public Op metric(int mertic) {
        this.metric = mertic;
        return this;
    }

    @Override
    public int compareTo(Metric<Op> o) {
        return this.metric - o.metric();
    }

    public Optional<Op> up() {
        return this.up;
    }

    public Stream<Op> ups() {
        return StreamUtil.of(this.up.orElse(null), e -> e.up().orElse(null));
    }

    public abstract Selector selector();
}
