/*
 * Generated - testing becomes easier
 *
 * Copyright (C) 2020 mathter@mail.ru
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
package org.genthz.configuration.dsl.loly;

public class NegateSelector extends org.genthz.configuration.dsl.loly.Selector implements org.genthz.configuration.dsl.NegateSelector {

    private final Selector origin;

    private final boolean negateChain;

    public NegateSelector(Dsl dsl, Selector origin, boolean negateChain) {
        super(dsl, origin.name() + ".negate", origin.metrics(), origin.next());
        this.origin = origin;
        this.negateChain = negateChain;
    }

    @Override
    public org.genthz.configuration.dsl.Selector origin() {
        return this.origin;
    }

    @Override
    public boolean negateChain() {
        return this.negateChain;
    }
}
