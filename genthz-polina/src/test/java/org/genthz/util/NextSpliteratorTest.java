/*
 * Generated - testing becomes easier
 *
 * Copyright (C) 2020 mathter@mail.ru
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
package org.genthz.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

public class NextSpliteratorTest {
    private int i = 0;

    @BeforeEach
    public void before() {
        this.i = 0;
    }

    @Test
    public void test() {
        final A a = new A(new A(new A(new A(null, i++), i++), i++), i++);
        final java.util.stream.Stream<A> stream = StreamUtil.of(a, A::next);
        final List<A> list = stream.collect(Collectors.toList());

        Assertions.assertEquals(4, list.size());
        Assertions.assertEquals(3, list.get(0).i);
        Assertions.assertEquals(2, list.get(1).i);
        Assertions.assertEquals(1, list.get(2).i);
        Assertions.assertEquals(0, list.get(3).i);
    }
}

class A {

    final int i;

    final A next;

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
