/*
 * GenThz - testing becomes easier
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
package org.genthz.configuration.dsl;

import org.genthz.configuration.Filler;
import org.genthz.configuration.InstanceBuilder;
import org.genthz.context.context.Context;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.function.Function;

public class DefaultsDefault implements Defaults {

    @Override
    public InstanceBuilder<Collection> defaultCollectionInstanceBuilder() {
        return c -> new ArrayList(this.defaultCollectionSize(c).apply(c));
    }

    @Override
    public InstanceBuilder<List> defaultListInstanceBuilder() {
        return c -> new ArrayList();
    }

    @Override
    public InstanceBuilder<Set> defaultSetInstanceBuilder() {
        return c -> new HashSet();
    }

    @Override
    public InstanceBuilder<Queue> defaultQueueInstanceBuilder() {
        return c -> new ArrayDeque();
    }

    @Override
    public InstanceBuilder<Deque> defaultDequeInstanceBuilder() {
        return c -> new ArrayDeque();
    }

    @Override
    public <T extends Collection> Filler<T> defaultCollectionFiller() {
        return null;
    }

    @Override
    public <T extends Collection> Function<Context<T>, Integer> defaultCollectionSize(Context<T> c) {
        return ctx -> 5;
    }

    @Override
    public Function<Context<?>, Integer> defaultDeep() {
        return c -> 100;
    }
}
