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
package org.genthz.loly;

import org.genthz.Context;

import java.util.Objects;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

abstract class ClassSelector<T, B extends ClassSelector<?, ?>> extends Selector implements AsBoxed<B> {

    private static ReadWriteLock LOCK = new ReentrantReadWriteLock();

    private static Lock LOCK_READ = LOCK.readLock();

    private static Lock LOCK_WRITE = LOCK.writeLock();

    private B boxed;

    private final Supplier<B> boxedProducer;

    protected ClassSelector(String name, Function<Context<?>, Long> metrics, Selector next, Predicate<Context<?>> predicate, Supplier<B> boxedProducer) {
        super(name, metrics, next, predicate);

        this.boxedProducer = Objects.requireNonNull(boxedProducer);
    }

    @Override
    public B asBoxed() {
        try {
            LOCK_READ.lock();
            if (this.boxed == null) {
                try {
                    LOCK_READ.unlock();
                    LOCK_WRITE.lock();
                    this.boxed = this.boxedProducer.get();
                    LOCK_READ.lock();
                } finally {
                    LOCK_WRITE.unlock();
                }
            }

            return this.boxed;
        } finally {
            LOCK_READ.unlock();
        }
    }
}
