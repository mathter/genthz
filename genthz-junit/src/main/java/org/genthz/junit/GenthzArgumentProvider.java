package org.genthz.junit;

import org.genthz.ObjectFactory;
import org.genthz.ObjectFactoryProvider;
import org.genthz.context.InstanceContext;
import org.genthz.dasha.dsl.DashaDsl;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.support.AnnotationConsumer;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class GenthzArgumentProvider implements ArgumentsProvider, AnnotationConsumer<GenthzSource> {
    private int count;

    private ObjectFactory objectFactory;

    @Override
    public void accept(GenthzSource genthzSource) {
        try {
            this.count = genthzSource.count();
            this.objectFactory = genthzSource.objectFactoryProvider().newInstance().objectFactory();
        } catch (Exception e) {
            throw new IllegalStateException("Can't create GenthzArgumentProvider!", e);
        }
    }


    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
        final Method method = context.getRequiredTestMethod();

        return IntStream.range(0, this.count)
                .mapToObj(tuple -> {
                            final Parameter[] elements = method.getParameters();
                            return Arguments.of(
                                    IntStream.range(0, elements.length)
                                            .mapToObj(index -> this.argument(elements[index]))
                                            .toArray(cnt -> new Object[cnt])
                            );
                        }
                );
    }

    private Object argument(Parameter parameter) {
        final Object result;
        final InstanceContext context;
        final ObjectFactory objectFactory;
        final Type type = parameter.getParameterizedType();
        final GenthzParameter genthzParameter;

        if ((genthzParameter = parameter.getAnnotation(GenthzParameter.class)) != null) {
            objectFactory = new DashaDsl() {
                {
                    this.strict(type).use(
                            s -> {
                                try {
                                    s.instanceBuilder(genthzParameter.instanceBuilder().newInstance());

                                    if (!NoFiller.class.equals(genthzParameter.filler())) {
                                        s.filler(genthzParameter.filler().newInstance());
                                    }
                                } catch (Exception e) {
                                    throw new IllegalStateException("Can't create instanceBuilder of filler!", e);
                                }
                            }
                    );

                }
            }.objectFactory(this.objectFactory.generationProvider());
        } else {
            objectFactory = this.objectFactory;
        }

        if (type instanceof Class) {
            result = objectFactory.get((Class) type);
        } else if (type instanceof ParameterizedType) {
            result = objectFactory.get(
                    (Class) ((ParameterizedType) type).getRawType(),
                    ((ParameterizedType) type).getActualTypeArguments()
            );
        } else if (type instanceof TypeVariable) {
            result = objectFactory.get(Object.class);
        } else {
            throw new IllegalStateException("Unsupported type: " + type);
        }

        return result;
    }
}
