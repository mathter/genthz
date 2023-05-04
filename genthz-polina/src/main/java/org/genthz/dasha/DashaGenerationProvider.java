package org.genthz.dasha;

import org.apache.commons.lang3.tuple.Pair;
import org.genthz.Defaults;
import org.genthz.GenerationProvider;
import org.genthz.context.InstanceContext;
import org.genthz.function.DefaultFiller;
import org.genthz.function.DefaultInstancebuilder;
import org.genthz.function.Filler;
import org.genthz.function.InstanceBuilder;
import org.genthz.function.Selector;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Optional;

public class DashaGenerationProvider implements GenerationProvider {
    private static final Comparator<Pair<Selector, ?>> COMPARATOR = Comparator.comparingInt(e -> e.getLeft().metric());

    private final Optional<GenerationProvider> up;

    private final Defaults defaults;

    private final Collection<Pair<Selector, InstanceBuilder>> instanceBuilders = new ArrayList<>();

    private final Collection<Pair<Selector, Filler>> filles = new ArrayList<>();

    public DashaGenerationProvider() {
        this(null, new DashaDefaults());
    }

    public DashaGenerationProvider(GenerationProvider up, Defaults defaults) {
        this.up = Optional.ofNullable(up);
        this.defaults = defaults != null ? defaults : new DashaDefaults();
    }

    @Override
    public <T> InstanceBuilder<T> instanceBuilder(InstanceContext context) {
        return this.instanceBuilders.stream()
                .filter(e -> e.getLeft().test(context))
                .max(COMPARATOR)
                .map(Pair::getRight)
                .orElseGet(() -> this.up.map(e -> e.instanceBuilder(context)).orElseGet(() -> new DefaultInstancebuilder<>()));
    }

    @Override
    public <T> Filler<T> filler(InstanceContext context) {
        return this.filles.stream()
                .filter(e -> e.getLeft().test(context))
                .max(COMPARATOR)
                .map(Pair::getRight)
                .orElseGet(() -> this.up.map(e -> e.filler(context)).orElseGet(() -> new DefaultFiller()));
    }

    @Override
    public Defaults defaults() {
        return this.defaults;
    }

    @Override
    public Optional<GenerationProvider> up() {
        return this.up;
    }
}
