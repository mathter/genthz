package org.genthz.dasha.function;

import org.genthz.GenerationProvider;
import org.genthz.ObjectFactory;
import org.genthz.context.ContextFactory;
import org.genthz.context.InstanceContext;
import org.genthz.context.NodeInstanceContext;
import org.genthz.dasha.DashaObjectFactory;
import org.genthz.dasha.dsl.DashaDsl;
import org.genthz.function.ContainerSize;
import org.genthz.function.DefaultArrayInstanceBuilder;
import org.genthz.function.DefaultsContainerSize;
import org.genthz.function.Filler;
import org.genthz.function.InstanceBuilder;
import org.genthz.function.Tail;
import org.genthz.function.UnitFiller;

import java.util.List;

/**
 * Predefined fillers for arrays.
 */
public abstract class ArrayTails {
    /**
     * Method creates a filler for a collection having selected count of an elements.
     *
     * @param size count of collection elements.
     * @param <T>  type of collection.
     * @return filler.
     */
    public static <T> S3IF<T> size(int size) {
        return new TailImpl(size, null, null);
    }

    /**
     * Method creates a filler which create an instances of collection components using selected {@linkplain InstanceBuilder}.
     *
     * @param function instance builder.
     * @param <T>      type of the collection.
     * @return filler of the collection.
     */
    public static <T> I3SF<T> componentInstanceBuilder(InstanceBuilder<T> function) {
        return new TailImpl(null, function, null);
    }

    /**
     * Method creates a filler which creates an instance of collection components using selected {@linkplain InstanceBuilder} and {@linkplain UnitFiller}.
     *
     * @param function instance builder.
     * @param <T>      type of the collection.
     * @return filler of the collection.
     */
    public static <T> I3S<T> componentSimple(InstanceBuilder<T> function) {
        return new TailImpl(null, function, UnitFiller.INSTANCE);
    }

    /**
     * Methods creates a filler which creates an instance of collection components using selected {@linkplain Filler}.
     *
     * @param function filler.
     * @param <T>      type of the collection.
     * @return filler of the collection.
     */
    public static <T> F3SI<T> componentFiller(Filler<T> function) {
        return new TailImpl(null, null, function);
    }

    public interface S3IF<T> extends Tail<T[]> {
        /**
         * Method creates a filler which create an instances of collection components using selected {@linkplain InstanceBuilder}.
         *
         * @param function instance builder.
         * @return filler of the collection.
         */
        public F<T> componentInstanceBuilder(InstanceBuilder<T> function);

        /**
         * Method creates a filler which creates an instance of collection components using selected {@linkplain InstanceBuilder} and {@linkplain UnitFiller}.
         *
         * @param function instance builder.
         * @return filler of the collection.
         */
        public Tail<T[]> componentSimple(InstanceBuilder<T> function);

        /**
         * Methods creates a filler which creates an instance of collection components using selected {@linkplain Filler}.
         *
         * @param function filler.
         * @return filler of the collection.
         */
        public I<T> componentFiller(Filler<T> function);
    }

    public interface I3SF<T> extends Tail<T[]> {
        /**
         * Method creates a filler for a collection having selected count of an elements.
         *
         * @param size count of collection elements.
         * @return filler.
         */
        public F<T> size(int size);

        /**
         * Methods creates a filler which creates an instance of collection components using selected {@linkplain Filler}.
         *
         * @param function filler.
         * @return filler of the collection.
         */
        public S<T> componentFiller(Filler<T> function);
    }

    public interface F3SI<T> extends Tail<T[]> {
        /**
         * Method creates a filler for a collection having selected count of an elements.
         *
         * @param size count of collection elements.
         * @return filler.
         */
        public I<T> size(int size);

        /**
         * Method creates a filler which create an instances of collection components using selected {@linkplain InstanceBuilder}.
         *
         * @param function instance builder.
         * @return filler of the collection.
         */
        public S<T> componentInstanceBuilder(InstanceBuilder<T> function);
    }

    public interface I3S<T> extends Tail<T[]> {
        /**
         * Method creates a filler for a collection having selected count of an elements.
         *
         * @param size count of collection elements.
         * @return filler.
         */
        public F<T> size(int size);
    }

    public interface I<T> extends Tail<T[]> {
        /**
         * Method creates a filler which create an instances of collection components using selected {@linkplain InstanceBuilder}.
         *
         * @param function instance builder.
         * @return filler of the collection.
         */
        public Tail<T[]> componentInstanceBuilder(InstanceBuilder<T> function);
    }

    public interface F<T> extends Tail<T[]> {
        /**
         * Methods creates a filler which creates an instance of collection components using selected {@linkplain Filler}.
         *
         * @param function filler.
         * @return filler of the collection.
         */
        public Tail<T[]> componentFiller(Filler<T> function);
    }

    public interface S<T> extends Tail<T[]> {
        /**
         * Method creates a filler for a collection having selected count of an elements.
         *
         * @param size count of collection elements.
         * @return filler.
         */
        public Tail<T[]> size(int size);
    }

    private static class TailImpl<T> implements Tail<T[]>, S3IF<T>, I3SF<T>, F3SI<T>, I3S<T>, S<T>, I<T>, F<T> {
        private ContainerSize<InstanceContext<T[]>> containerSize;

        private InstanceBuilder<T[]> instanceBuilder;

        private InstanceBuilder<T> componentInstanceBuilder;

        private Filler<T> componentFiller;

        public TailImpl(Integer size, InstanceBuilder<T> componentInstanceBuilder, Filler<T> componentFiller) {
            this.setSize(size);
            this.componentInstanceBuilder = componentInstanceBuilder;
            this.componentFiller = componentFiller;
        }

        @Override
        public InstanceBuilder<T[]> instanceBuilder() {
            return this.instanceBuilder;
        }

        @Override
        public Filler<T[]> filler() {
            return new Filler<T[]>() {
                @Override
                public void fill(InstanceContext<T[]> context) {
                    if (context.get() != null) {
                        final ContextFactory contextFactory = context.contextFactory();
                        final ObjectFactory objectFactory = context.objectFactory();
                        final GenerationProvider generationProvider = objectFactory.generationProvider();
                        final int size = TailImpl.this.containerSize.get(context);

                        if (size > 0) {
                            final List<NodeInstanceContext<Object, Integer>> contexts = contextFactory.byArray(context, size);

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
            };
        }

        private void setSize(Integer size) {
            this.containerSize = size != null ? context -> size : new DefaultsContainerSize();
            this.instanceBuilder = new DefaultArrayInstanceBuilder<>(this.containerSize);
        }

        @Override
        public TailImpl size(int size) {
            this.setSize(size);
            return this;
        }

        @Override
        public TailImpl componentInstanceBuilder(InstanceBuilder<T> function) {
            this.componentInstanceBuilder = function;
            return this;
        }

        @Override
        public TailImpl componentSimple(InstanceBuilder<T> function) {
            this.componentInstanceBuilder = function;
            this.componentFiller = UnitFiller.INSTANCE;
            return this;
        }

        @Override
        public TailImpl componentFiller(Filler<T> function) {
            this.componentFiller = function;
            return this;
        }
    }

    private ArrayTails() {
    }
}
