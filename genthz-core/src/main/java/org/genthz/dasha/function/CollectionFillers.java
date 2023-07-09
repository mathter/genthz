/*
 * Generated - testing becomes easier
 *
 * Copyright (C) 2023 mathter@mail.ru
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
package org.genthz.dasha.function;

import org.genthz.GenerationProvider;
import org.genthz.ObjectFactory;
import org.genthz.context.ContextFactory;
import org.genthz.context.InstanceContext;
import org.genthz.context.NodeInstanceContext;
import org.genthz.dasha.DashaObjectFactory;
import org.genthz.dasha.dsl.DashaDsl;
import org.genthz.function.Filler;
import org.genthz.function.InstanceBuilder;
import org.genthz.function.UnitFiller;

import java.util.Collection;
import java.util.List;

/**
 * Predefined fillers for {@linkplain Collection}.
 *
 * @since 3.0.3
 */
public abstract class CollectionFillers {
    /**
     * Method creates a filler for a collection having selected count of an elements.
     *
     * @param size count of collection elements.
     * @param <T>  type of collection.
     * @return filler.
     */
    public static <T extends Collection> S3IF<T> size(int size) {
        return new FillerImpl(size, null, null);
    }

    /**
     * Method creates a filler which create an instances of collection components using selected {@linkplain InstanceBuilder}.
     *
     * @param function instance builder.
     * @param <T>      type of the collection.
     * @param <C>      type of the collection components.
     * @return filler of the collection.
     */
    public static <T extends Collection<C>, C> I3SF<T, C> componentInstanceBuilder(InstanceBuilder<C> function) {
        return new FillerImpl(null, function, null);
    }

    /**
     * Method creates a filler which creates an instance of collection components using selected {@linkplain InstanceBuilder} and {@linkplain UnitFiller}.
     *
     * @param function instance builder.
     * @param <T>      type of the collection.
     * @param <C>      type of the collection components.
     * @return filler of the collection.
     */
    public static <T extends Collection<C>, C> I3S<T, C> componentSimple(InstanceBuilder<C> function) {
        return new FillerImpl(null, function, UnitFiller.INSTANCE);
    }

    /**
     * Methods creates a filler which creates an instance of collection components using selected {@linkplain Filler}.
     *
     * @param function filler.
     * @param <T>      type of the collection.
     * @param <C>      type of the collection components.
     * @return filler of the collection.
     */
    public static <T extends Collection<C>, C> F3SI<T, C> componentFiller(Filler<C> function) {
        return new FillerImpl<>(null, null, function);
    }

    public interface S3IF<T> extends Filler<T> {
        /**
         * Method creates a filler which create an instances of collection components using selected {@linkplain InstanceBuilder}.
         *
         * @param function instance builder.
         * @param <C>      type of the collection components.
         * @return filler of the collection.
         */
        public <C> F<T, C> componentInstanceBuilder(InstanceBuilder<C> function);

        /**
         * Method creates a filler which creates an instance of collection components using selected {@linkplain InstanceBuilder} and {@linkplain UnitFiller}.
         *
         * @param function instance builder.
         * @param <C>      type of the collection components.
         * @return filler of the collection.
         */
        public <C> Filler<T> componentSimple(InstanceBuilder<C> function);

        /**
         * Methods creates a filler which creates an instance of collection components using selected {@linkplain Filler}.
         *
         * @param function filler.
         * @param <C>      type of the collection components.
         * @return filler of the collection.
         */
        public <C> I<T, C> componentFiller(Filler<C> function);
    }

    public interface I3SF<T, C> extends Filler<T> {
        /**
         * Method creates a filler for a collection having selected count of an elements.
         *
         * @param size count of collection elements.
         * @return filler.
         */
        public F<T, C> size(int size);

        /**
         * Methods creates a filler which creates an instance of collection components using selected {@linkplain Filler}.
         *
         * @param function filler.
         * @return filler of the collection.
         */
        public S<T> componentFiller(Filler<C> function);
    }

    public interface I3S<T, C> extends Filler<T> {
        /**
         * Method creates a filler for a collection having selected count of an elements.
         *
         * @param size count of collection elements.
         * @return filler.
         */
        public F<T, C> size(int size);
    }

    public interface F3SI<T, C> extends Filler<T> {
        /**
         * Method creates a filler for a collection having selected count of an elements.
         *
         * @param size count of collection elements.
         * @return filler.
         */
        public I<T, C> size(int size);

        /**
         * Method creates a filler which create an instances of collection components using selected {@linkplain InstanceBuilder}.
         *
         * @param function instance builder.
         * @return filler of the collection.
         */
        public S<T> componentInstanceBuilder(InstanceBuilder<C> function);
    }

    public interface I<T, C> extends Filler<T> {
        /**
         * Method creates a filler which create an instances of collection components using selected {@linkplain InstanceBuilder}.
         *
         * @param function instance builder.
         * @return filler of the collection.
         */
        public Filler<T> componentInstanceBuilder(InstanceBuilder<C> function);
    }

    public interface F<T, C> extends Filler<T> {
        /**
         * Methods creates a filler which creates an instance of collection components using selected {@linkplain Filler}.
         *
         * @param function filler.
         * @return filler of the collection.
         */
        public Filler<T> componentFiller(Filler<C> function);
    }

    public interface S<T> extends Filler<T> {
        /**
         * Method creates a filler for a collection having selected count of an elements.
         *
         * @param size count of collection elements.
         * @return filler.
         */
        public Filler<T> size(int size);
    }

    private static class FillerImpl<T extends Collection, C> implements
            Filler<T>,
            S3IF<T>,
            I3SF<T, C>,
            F3SI<T, C>,
            I3S<T, C>,
            F<T, C>,
            I<T, C>,
            S<T> {
        private Integer size;

        private InstanceBuilder<C> componentInstanceBuilder;

        private Filler<C> componentFiller;

        public FillerImpl(Integer size, InstanceBuilder<C> componentInstanceBuilder, Filler<C> componentFiller) {
            this.size = size;
            this.componentInstanceBuilder = componentInstanceBuilder;
            this.componentFiller = componentFiller;
        }

        @Override
        public void fill(InstanceContext context) {
            if (context.get() != null) {
                final ContextFactory contextFactory = context.contextFactory();
                final ObjectFactory objectFactory = context.objectFactory();
                final GenerationProvider generationProvider = objectFactory.generationProvider();

                if (this.size == null) {
                    this.size = objectFactory.generationProvider().defaults().defaultCollectionSize();
                }

                if (this.size > 0) {
                    final List<NodeInstanceContext<Object, Integer>> contexts = contextFactory.byCollection(context, this.size);

                    final ObjectFactory tmpObjectFactory = new DashaObjectFactory(new DashaDsl() {
                        {
                            this.strict(contexts.get(0).type())
                                    .m(Integer.MAX_VALUE)
                                    .use(selector -> {
                                        if (componentInstanceBuilder != null) {
                                            selector.instanceBuilder((InstanceBuilder<Object>) componentInstanceBuilder);
                                        }

                                        if (componentFiller != null) {
                                            selector.filler((Filler<Object>) componentFiller);
                                        }
                                    });
                        }
                    }.build(generationProvider));

                    contexts.forEach(tmpObjectFactory::process);
                }
            }
        }

        @Override
        public FillerImpl componentInstanceBuilder(InstanceBuilder function) {
            this.componentInstanceBuilder = function;
            return this;
        }

        @Override
        public FillerImpl componentSimple(InstanceBuilder function) {
            this.componentInstanceBuilder = function;
            this.componentFiller = UnitFiller.INSTANCE;
            return this;
        }

        @Override
        public FillerImpl componentFiller(Filler function) {
            this.componentFiller = function;
            return this;
        }

        @Override
        public FillerImpl size(int size) {
            this.size = size;
            return this;
        }
    }

    private CollectionFillers() {
    }
}
