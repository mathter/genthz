/*
 * Generated - testing becomes easier
 *
 * Copyright (C) 2023 mathter@mail.ru
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
 * public class MyTest {
 *     {@literal @}ParameterizedTest
 *     {@literal @}GenthzSource
 *      public void test({@literal @}GenthzParameter(instanceBuilder = CustomInstanceBuilder.class) String name) {
 *          ...
 *      }
 * }
 *
 * public class CustomInstanceBuilder implements InstanceBuilder&lt;String&gt; {
 *      public String instance(InstanceContext&lt;String&gt; context) {
 *          return "String value";
 *      }
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
