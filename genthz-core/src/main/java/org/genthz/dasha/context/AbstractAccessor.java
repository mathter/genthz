package org.genthz.dasha.context;

import org.genthz.context.Accessor;
import org.genthz.context.Stage;

import java.util.Objects;

public abstract class AbstractAccessor<T> implements Accessor<T> {
    private Stage stage = Stage.NEW;

    @Override
    public Stage stage() {
        return this.stage;
    }

    @Override
    public void stage(Stage stage) {
        this.stage = Objects.requireNonNull(stage);
    }
}
