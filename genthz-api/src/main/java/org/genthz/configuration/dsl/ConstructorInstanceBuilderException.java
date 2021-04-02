package org.genthz.configuration.dsl;

/**
 * @author <a href="mailto:mathter@mail.ru">mathter</a>
 * @version 1.0.0
 * @since 1.0.0
 */
public abstract class ConstructorInstanceBuilderException extends RuntimeException {
    public ConstructorInstanceBuilderException() {
    }

    public ConstructorInstanceBuilderException(String message) {
        super(message);
    }

    public ConstructorInstanceBuilderException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConstructorInstanceBuilderException(Throwable cause) {
        super(cause);
    }

    public ConstructorInstanceBuilderException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
