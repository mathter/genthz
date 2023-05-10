package org.genthz;

public interface Now {
    public ObjectFactory objectFactory();

    default public GenerationProvider generationProvider() {
        return this.objectFactory().generationProvider();
    }

    default public Defaults defaults() {
        return this.generationProvider().defaults();
    }
}
