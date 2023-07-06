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
package org.genthz.dasha.context;

import org.genthz.FieldMatchers;
import org.genthz.context.AccessorResolver;
import org.genthz.context.ContextFactory;
import org.genthz.etalon.EtalonContextFactoryTest;
import org.genthz.function.FieldMatcher;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

public class EtalonDashaContextFactoryTest extends EtalonContextFactoryTest {
    @Override
    protected ContextFactory contextFactory() {
        return new DashaContextFactory();
    }

    @Override
    protected AccessorResolver accessorResolver(Collection<String> includes, Collection<String> excludes) {
        return new DashaAccessorResolver(
                Optional.ofNullable(includes)
                        .map(e -> e.stream()
                                .distinct()
                                .map(ee -> (FieldMatcher) FieldMatchers.name(ee))
                                .collect(Collectors.toList()))
                        .orElse(null),
                Optional.ofNullable(excludes)
                        .map(e -> e.stream()
                                .distinct()
                                .map(ee -> (FieldMatcher) FieldMatchers.name(ee))
                                .collect(Collectors.toList()))
                        .orElse(null)
        );
    }
}
