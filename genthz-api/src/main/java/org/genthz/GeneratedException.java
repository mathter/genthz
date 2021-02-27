package org.genthz;

public class GeneratedException extends RuntimeException {

    public GeneratedException() {
    }

    public GeneratedException(String message) {
        super(message);
    }

    public GeneratedException(String message, Throwable cause) {
        super(message, cause);
    }

    public GeneratedException(Throwable cause) {
        super(cause);
    }

    public GeneratedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
