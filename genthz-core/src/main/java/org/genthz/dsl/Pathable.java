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
package org.genthz.dsl;

import org.genthz.reflection.reference.GetMethodReference;
import org.genthz.reflection.reference.SetMethodReference;

/**
 * This class contains methods that make it possible to create context selectors based on the paths.
 *
 * @author mathter
 * @since 3.0.0
 */
public interface Pathable {
    /**
     * The method is short alias for {@linkplain #path(String)}.
     */
    default public <S extends Pathable & Strictable & Unstricable & Customable & InstanceBuilderFirst & FillerFirst & Metric<S> & Using<S>> S p(String path) {
        return this.path(path);
    }

    /**
     * Creates selector builder based on paths. Fields, indexes of collection elements or arrays can be used.
     * <pre>
     *     public class TestClass {
     *         private String myField;
     *         ...
     *     }
     *
     *     Dsl dsl = new DashaDsl()
     *          .defs();                        // create default rules for object creation.
     *
     *     dsl.strict(TestClass.class)     // matche only TestClass.class
     *        .path("myField")                // matche fiedls with name "myField"
     *        .simple(ctx -&gt; "My value");     // create instance builder with fixed generated value: "My values"
     * </pre>
     *
     * @param path path.
     * @param <S>  type of selector builder.
     * @return selector builder.
     */
    public <S extends Pathable & Strictable & Unstricable & Customable & InstanceBuilderFirst & FillerFirst & Metric<S> & Using<S>> S path(String path);

    /**
     * Creates selector builder based on path described by method parameters.
     * Next two calls are equivalent:
     * <pre>
     *     public class TestClass {
     *          private String myField;
     *
     *          public String getMyField() {
     *              return this.myField;
     *          }
     *          ...
     *     }
     *
     *     Dsl dsl = new DashaDsl()
     *          .defs();                                    // create default rules for object creation.
     *
     *          dsl.path(TestClass::getMyField);            // (1) first form.
     *          dsl.path("myField").strict(String.class);   // (2) second form are equivalent to (1) first one.
     * </pre>
     *
     * @param reference method reference.
     * @param <S>       type of selector builder.
     * @return selector builder.
     */
    public <S extends Pathable & InstanceBuilderFirst & FillerFirst & Metric<S> & Using<S>> S path(final GetMethodReference reference);

    /**
     * Creates selector builder based on path described by method parameters.
     * Next two calls are equivalent:
     * <pre>
     *     public class TestClass {
     *          private String myField;
     *
     *          public String setMyField() {
     *              return this.myField;
     *          }
     *          ...
     *     }
     *
     *     Dsl dsl = new DashaDsl()
     *          .defs();                                    // create default rules for object creation.
     *
     *          dsl.path(TestClass::setMyField);            // (1) first form.
     *          dsl.path("myField").strict(String.class);   // (2) second form are equivalent to (1) first one.
     * </pre>
     *
     * @param reference method reference.
     * @param <S>       type of selector builder.
     * @return selector builder.
     */
    public <S extends Pathable & InstanceBuilderFirst & FillerFirst & Metric<S> & Using<S>> S path(SetMethodReference reference);
}
