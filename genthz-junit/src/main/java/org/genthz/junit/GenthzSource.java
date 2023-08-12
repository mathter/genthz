package org.genthz.junit;

import org.genthz.ObjectFactory;
import org.genthz.ObjectFactoryProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This annotation is an {@linkplain ArgumentsSource} which provides access to values returned by {@linkplain #objectFactoryProvider()}.
 * If test method is annotated with annotation {@linkplain GenthzSource} annotation {@linkplain GenthzParameter} can be used
 * to annotate the parameters of this test method.
 * <pre>
 * {@code
 * public class MyTest {
 *      @ParameterizedTest
 *      @GenthzSource
 *      public void test(@GenthzParameter(instanceBuilder = CustomInstanceBuilder.class) String name) {
 *          ...
 *      }
 * }
 *
 * public class CustomInstanceBuilder implements InstanceBuilder<String> {
 *      public String instance(InstanceContext<String> context) {
 *          return "String value";
 *      }
 * }
 * }
 * </pre>
 * @since 3.1.0
 */
@Target({ElementType.ANNOTATION_TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ArgumentsSource(GenthzArgumentProvider.class)
public @interface GenthzSource {
    /**
     * Method returns count of tuples will be returned by provider.
     *
     * @return number of the tuples.
     */
    public int count() default 1;

    /**
     * Method returns {@linkplain ObjectFactory} which will be used to generate data.
     *
     * @return object factory.
     */
    public Class<? extends ObjectFactoryProvider> objectFactoryProvider() default DefaultObjectFactoryProvider.class;
}
