package org.genthz.dasha;

import org.genthz.context.Accessor;
import org.genthz.context.Stage;

public abstract class AbstractAccessor<T> implements Accessor<T> {
    private Stage stage = Stage.NEW;

    @Override
    public Stage stage() {
        return this.stage;
    }

    @Override
    public void init() {
        this.stage = Stage.CRETING;
    }

    @Override
    public void set(T value) throws IllegalStateException {
        switch (this.stage) {
            case CRETING:
                this.stage = Stage.CREATED;
                break;
            case CREATED:
                this.stage = Stage.COMPLETE;
                break;
            default:
                throw new IllegalStateException(String.format("Invalid transition! Current stage=%s", this.stage));
        }
    }
}
