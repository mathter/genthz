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
package org.genthz.dasha.function;

import org.genthz.context.Accessor;
import org.genthz.context.AccessorResolver;
import org.genthz.context.InstanceContext;
import org.genthz.context.Node;
import org.genthz.context.Typeable;
import org.genthz.dasha.context.DashaAccessorResolver;
import org.genthz.function.DefaultFiller;
import org.genthz.function.Filler;

import java.util.Collection;

public abstract class Fillers {
    public static <T> Filler<T> include(String... fieldNames) {
        return new DefaultFiller() {
            @Override
            public void fill(InstanceContext context) {
                super.fill(context);
            }
        };
    }

    private Fillers() {
    }

    private static class FlammableAccessorResolver extends DashaAccessorResolver {
        private final AccessorResolver wrapperd;

        private boolean borned = false;

        public FlammableAccessorResolver(AccessorResolver wrapperd) {
            this.wrapperd = wrapperd;
        }

        @Override
        public <A extends Accessor & Node<String> & Typeable> Collection<A> resolve(InstanceContext up) {
            return this.borned ? this.wrapperd.resolve(up) : super.resolve(up);
        }
    }
}
