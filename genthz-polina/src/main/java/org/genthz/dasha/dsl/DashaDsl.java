package org.genthz.dasha.dsl;

import org.genthz.GenerationProvider;
import org.genthz.context.Context;
import org.genthz.dasha.DashaDefaults;
import org.genthz.dasha.DashaGenerationProvider;
import org.genthz.dsl.Customable;
import org.genthz.dsl.Dsl;
import org.genthz.dsl.FillerFirst;
import org.genthz.dsl.InstanceBuilderFirst;
import org.genthz.dsl.Pathable;
import org.genthz.dsl.Strictable;
import org.genthz.dsl.Unstricable;
import org.genthz.function.Filler;
import org.genthz.function.InstanceBuilder;
import org.genthz.function.Selector;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public class DashaDsl implements Dsl {
    private Map<Selector, InstanceBuilder> builderMap = new HashMap<>();

    private Map<Selector, Filler> fillerMap = new HashMap<>();

    void reg(Selector selector, InstanceBuilder instanceBuilder) {
        this.builderMap.put(selector, instanceBuilder);
    }

    void reg(Selector selector, Filler filler) {
        this.fillerMap.put(selector, filler);
    }

    @Override
    public <S extends Pathable & Strictable & Unstricable & InstanceBuilderFirst & FillerFirst> S custom(Predicate<Context> predicate) {
        return (S) new CustomOps(null, predicate);
    }

    @Override
    public <S extends Pathable & Strictable & Unstricable & Customable & InstanceBuilderFirst & FillerFirst> S path(String path) {
        return (S) new PathOp(null, path);
    }

    @Override
    public <T, S extends Pathable & Customable & InstanceBuilderFirst & FillerFirst> S strict(Type type, Type... genericTypeArgs) {
        return (S) new StrictTypeOp(null, type, genericTypeArgs);
    }

    @Override
    public <T, S extends Pathable & Customable & InstanceBuilderFirst & FillerFirst> S unstrict(Type type, Type... genericTypeArgs) {
        return (S) new UnstrictTypeOp(null, type, genericTypeArgs);
    }

    @Override
    public GenerationProvider build(GenerationProvider parent) {
        final DashaGenerationProvider provider = new DashaGenerationProvider(parent, new DashaDefaults());

        return provider;
    }
}
