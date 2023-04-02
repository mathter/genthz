package org.genthz.context;

public class ContextException extends IllegalStateException {
    public ContextException() {
    }

    public ContextException(String s) {
        super(s);
    }

    public ContextException(String message, Throwable cause) {
        super(message, cause);
    }

    public ContextException(Throwable cause) {
        super(cause);
    }
}
