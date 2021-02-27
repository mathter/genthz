package org.genthz;

public class ProducerNotFoundException extends GeneratedException {
    private final String id;

    public ProducerNotFoundException(String id) {
        super(message(id), null, false, false);
        this.id = id;
    }

    private static String message(String factoryName) {
        return String.format("There is no factory with name equals to '%s'!", factoryName != null ? factoryName : "<null>");
    }

    public String getId() {
        return id;
    }
}
