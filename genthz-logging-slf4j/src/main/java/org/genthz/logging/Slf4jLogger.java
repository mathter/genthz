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
package org.genthz.logging;

import org.apache.commons.lang3.tuple.Pair;
import org.genthz.context.Context;
import org.genthz.diagnostic.DiganosticInfo;
import org.genthz.function.Filler;
import org.genthz.function.InstanceBuilder;
import org.genthz.function.Selector;
import org.slf4j.LoggerFactory;

class Slf4jLogger implements Logger {
    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(Slf4jLogger.class.getName());

    @Override
    public void logCreateSelectable(Pair<Selector, ?> pair) {
        LOG.debug(
                "Selectable created for: selector: {} with target: {}",
                pair.getLeft(),
                pair.getRight() instanceof DiganosticInfo ? ((DiganosticInfo) pair.getRight()).getDiagnosticInfo() : pair.getRight().toString()
        );
    }

    @Override
    public void logInstanceBuilderWillBeUsed(Context context, Pair<Selector, InstanceBuilder> pair) {
        LOG.debug(
                "The following selector: {} and instance builder {} will be used for context: {}",
                pair.getLeft(),
                pair.getRight() instanceof DiganosticInfo ? ((DiganosticInfo) pair.getRight()).getDiagnosticInfo() : pair.getRight().toString(),
                context
        );
    }

    @Override
    public void logFillerBuilderWillBeUsed(Context context, Pair<Selector, Filler> pair) {
        LOG.debug(
                "The following selector: {} and instance builder {} will be used for context: {}",
                pair.getLeft(),
                pair.getRight() instanceof DiganosticInfo ? ((DiganosticInfo) pair.getRight()).getDiagnosticInfo() : pair.getRight().toString(),
                context
        );
    }
}
