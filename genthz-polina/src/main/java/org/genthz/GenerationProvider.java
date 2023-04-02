package org.genthz;

import org.genthz.context.ConstructorContext;
import org.genthz.function.InstanceBuilder;
import org.genthz.function.Filler;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

public interface GenerationProvider {
    public <T> InstanceBuilder<T> instanceBuilder(ConstructorContext<T, ?, ?> context);

    public <T> Filler<T> filler(ConstructorContext<T, ?, ?> context);

    public Optional<GenerationProvider> generationProvider();

    public Defaults defaults();

    public Optional<GenerationProvider> up();
}
