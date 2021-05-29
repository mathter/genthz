package org.genthz.configuration.dsl;

/**
 * Interface represent negate selector.
 *
 * @author <a href="mailto:mathter@mail.ru">mathter</a>
 * @version 1.0.0
 * @since 1.0.0
 */
public interface NegateSelector extends Selector {

    /**
     * The original selector to which the negation operation will be applied.
     *
     * @return origin selector.
     */
    public Selector origin();

    /**
     * <code>true</code> - negation operation will be applied to {@linkplain #origin()} selector and its parent
     * (all selectors chain {@linkplain #next()}.
     * <code>false</code> -negation operation will be applied to {@linkplain #origin()} selector only.
     *
     * @return flag.
     */
    public boolean negateChain();
}
