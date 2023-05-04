package org.genthz.dasha.dsl;

class Op<T extends Op<T>> {
    private final T up;

    public Op(T up) {
        this.up = up;
    }

    public T up() {
        return up;
    }

    public DashaDsl dsl() {
        final DashaDsl result;

        if (this.up != null) {
            result = this.up.dsl();
        } else {
            throw new IllegalStateException();
        }

        return result;
    }
}
