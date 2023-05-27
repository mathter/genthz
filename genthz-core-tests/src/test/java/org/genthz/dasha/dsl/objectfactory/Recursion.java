package org.genthz.dasha.dsl.objectfactory;

public class Recursion {
    private Recursion next;

    public Recursion getNext() {
        return next;
    }

    public void setNext(Recursion next) {
        this.next = next;
    }
}
