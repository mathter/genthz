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
package org.genthz.loly.context;

import org.genthz.Bindings;
import org.genthz.Context;
import org.genthz.ObjectFactory;
import org.genthz.loly.reflect.Accessor;

import java.lang.reflect.Array;
import java.util.Collection;

public class CollectionFakeContext<T, C> extends ValueContext<C> implements Context<C>, Accessor<C> {

    private static final TripleConsumer<Collection, Object> ADD_TO_COLLECTION = (c, i, e) -> c.add(e);

    private static final TripleConsumer<Object, Object> ADD_TO_ARRAY = (c, i, e) -> Array.set(c, i, e);

    private final Context<T> collectionContext;

    private final Class<C> componentClass;
    private final int index;
    private final TripleConsumer<T, C> addTo;
    private C object;
    private Stage stage = Stage.NEW;

    public CollectionFakeContext(
            ObjectFactory objectFactory,
            ValueContext<?> parent,
            Context<T> collectionContext,
            Class<C> componentClass,
            int index
    ) {
        super(objectFactory, parent);
        this.collectionContext = collectionContext;
        this.componentClass = componentClass;
        this.index = index;
        this.addTo = addToCollection(collectionContext.node());
    }

    @Override
    public Bindings bindings() {
        return this.collectionContext.bindings();
    }

    @Override
    public ObjectFactory objectFactory() {
        return this.collectionContext.objectFactory();
    }

    @Override
    public Class<C> clazz() {
        return this.componentClass;
    }

    @Override
    public C node() {
        return this.get();
    }

    @Override
    public String name() {
        return this.collectionContext.name();
    }

    @Override
    public Collection<? extends Context<?>> childs() {
        // TODO
        return null;
    }

    @Override
    public C get() {
        return this.object;
    }

    @Override
    public void setInstance(C value) {
        this.object = value;
        this.stage = Stage.INITIALIZATION;
    }

    @Override
    public void setFilled(C value) {
        this.object = value;
        this.addTo.accept(this.collectionContext.node(), this.index, value);
        this.stage = Stage.COMPLETE;
    }

    private TripleConsumer<T, C> addToCollection(T collection) {
        final TripleConsumer<T, C> result;

        if (collection instanceof Collection) {
            result = (TripleConsumer<T, C>) ADD_TO_COLLECTION;
        } else if (collection.getClass().isArray()) {
            result = (TripleConsumer<T, C>) ADD_TO_ARRAY;
        } else {
            throw new IllegalArgumentException(collection + " must be instance of java.util.Collection or array!");
        }

        return result;
    }

    @FunctionalInterface
    public interface TripleConsumer<T, C> {
        public void accept(T collection, Integer index, C component);
    }
}
