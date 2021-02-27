package org.genthz.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class NextSpliteratorTest {
    private int i = 0;

    @BeforeEach
    public void before() {
        this.i = 0;
    }

    @Test
    public void test() {
        A a = new A(new A(new A(new A(null, i++), i++), i++), i++);

        Stream
                .of(a, A::next)
                .forEach(e -> System.out.println(e));
    }
}

class A {

    private final int i;

    private final A next;

    public A(A next, int i) {
        this.next = next;
        this.i = i;
    }

    public A next() {
        return this.next;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("A[");

        sb.append("a=").append(this.next);
        sb.append(", i=").append(this.i);
        sb.append("]");

        return sb.toString();
    }
}
