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
     * Creates selector builder pased on paths. Fields, indexes of collection elements or arrays can be used.
     * <pre>
     *     public class TestClass {
     *         private String myField;
     *         ...
     *     }
     *
     *     Dsl dsl = new DashaDsl()
     *          .defs();                         // create default rules for object creation.
     *
     *          dsl.strict(TestClass.class)     // matche only TestClass.class
     *          .path("myField")                // matche fiedls with name "myField"
     *          .simple(ctx -&gt; "My value");     // create instance builder with fixed generated value: "My values"
     * </pre>
     *
     * @param path path.
     * @param <S>  type of selector builder.
     * @return selector builder.
     */
    public <S extends Pathable & Strictable & Unstricable & Customable & InstanceBuilderFirst & FillerFirst & Metric<S> & Using<S>> S path(String path);
}
