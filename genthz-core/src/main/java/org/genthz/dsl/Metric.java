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
package org.genthz.dsl;

/**
 * This interface represents base property of the {@linkplain org.genthz.function.Selector}. Metric is an integer value.
 *
 * @param <T>
 */
public interface Metric<T extends Metric<T>> extends Comparable<Metric<T>> {

    /**
     * Short name of the {@linkplain #metric()}.
     */
    default public int m() {
        return this.metric();
    }

    /**
     * Method returns metric value for this selector.
     *
     * @return metric.
     */
    public int metric();

    /**
     * Short name of the {@linkplain #metric(int)}.
     */
    default public T m(int metric) {
        return this.metric(metric);
    }

    /**
     * This method sets metrics.
     *
     * @param metric metric value.
     * @return this object.
     */
    public T metric(int metric);

    /**
     * This method returns effective metrics. Effective metric is a sum of all own metrics of parent ones usually.
     *
     * @return effective metric.
     */
    default public int effective() {
        return this.metric();
    }
}
