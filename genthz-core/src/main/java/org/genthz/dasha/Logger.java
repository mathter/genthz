package org.genthz.dasha;

import org.apache.commons.lang3.tuple.Pair;
import org.genthz.context.Context;
import org.genthz.function.Filler;
import org.genthz.function.InstanceBuilder;
import org.genthz.function.Selector;
import org.slf4j.LoggerFactory;

public class Logger {
    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(Logger.class.getName());

    public static void logCreateSelectable(Pair<Selector, ?> pair) {
        LOG.debug(
                "Selectable created for: selector: {} with target: {}",
                pair.getLeft(),
                pair.getRight()
        );
    }

    public static void logInstanceBuilderWillBeUsed(Context context, Pair<Selector, InstanceBuilder> pair) {
        LOG.debug(
                "The following selector: {} and instance builder {} will be used for context: {}",
                pair.getLeft(),
                pair.getRight(),
                context
        );
    }

    public static void logFillerBuilderWillBeUsed(Context context, Pair<Selector, Filler> pair) {
        LOG.debug(
                "The following selector: {} and instance builder {} will be used for context: {}",
                pair.getLeft(),
                pair.getRight(),
                context
        );
    }
}
