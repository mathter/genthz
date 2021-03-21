package org.genthz.configuration.dsl;

public interface NegateSelector extends Selector {

    public Selector origin();

    public boolean negateChain();
}
