package org.genthz;

import org.genthz.context.InstanceContext;
import org.genthz.function.Filler;
import org.genthz.function.InstanceBuilder;

import java.util.Optional;

public interface GenerationProvider {
    public <T> InstanceBuilder<T> instanceBuilder(InstanceContext context);

    public <T> Filler<T> filler(InstanceContext context);

    public Optional<GenerationProvider> generationProvider();

    public Defaults defaults();

    public Optional<GenerationProvider> up();
}
