package org.genthz.simple;

import org.genthz.Defaults;
import org.genthz.GenerationProvider;
import org.genthz.context.ConstructorContext;
import org.genthz.function.Filler;
import org.genthz.function.InstanceBuilder;
import org.genthz.simple.SimpleDefaults;

import java.util.Collection;
import java.util.Optional;
import java.util.concurrent.ConcurrentLinkedDeque;

class SimpleGenerationProvider implements GenerationProvider {
    private final Defaults defaults;

    private final Optional<GenerationProvider> generationProvider;

    private final Collection<InstanceBuilder<?>> instanceBuilders = new ConcurrentLinkedDeque();

    private final Collection<Filler<?>> fillers = new ConcurrentLinkedDeque();

    private InstanceBuilder defaultCollectionInstanceBuilder;

    private InstanceBuilder defaultArrayInstanceBuilder;

    private InstanceBuilder defaultMapInstanceBuilder;

    public SimpleGenerationProvider() {
        this(null, new SimpleDefaults());
    }

    public SimpleGenerationProvider(GenerationProvider generationProvider, Defaults defaults) {
        this.generationProvider = Optional.ofNullable(generationProvider);
        this.defaults = defaults;
    }

    @Override
    public <T> InstanceBuilder<T> instanceBuilder(ConstructorContext<T, ?, ?> context) {
        return null;
    }

    @Override
    public <T> Filler<T> filler(ConstructorContext<T, ?, ?> context) {
        return null;
    }

    @Override
    public Optional<GenerationProvider> generationProvider() {
        return this.generationProvider;
    }

    @Override
    public Defaults defaults() {
        return null;
    }

    @Override
    public Optional<GenerationProvider> up() {
        return Optional.empty();
    }
}
