package org.genthz.dasha.diagnostic;

import java.util.stream.Stream;

public interface DiagnosticParameters {
    public Stream<Parameter> params();

    public interface Parameter<T> {
        static <T> Parameter<T> of(String name, T value) {
            return new ParameterImpl(name, value);
        }

        public String getName();

        public <T> T getValue();
    }
}
