package org.genthz.util.reflection;

import java.util.Collection;

public class A<T1, T2> {
    private B<? extends T1, String> b1;

    private B<String, T2> b2;

    private B<String, Integer> b3;

    private BB bb;
}

class AA extends A<String, Long> {
}

class B<T1, T2> {
    private C<T1> f0;

    private T2 f1;

    private String f2;

    public B() {
    }

    public B(C<T1> f0, T2 f1, String f2) {
        this.f0 = f0;
        this.f1 = f1;
        this.f2 = f2;
    }
}

class BB extends B<String, Long> {
}

class C<T> {
    private Collection<T> f;
}

class D<T> extends C<String> implements I<T> {
}