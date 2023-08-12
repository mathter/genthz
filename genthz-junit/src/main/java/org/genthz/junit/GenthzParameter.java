package org.genthz.junit;

import org.genthz.function.Filler;
import org.genthz.function.InstanceBuilder;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @since 3.1.0
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface GenthzParameter {
    public Class<? extends InstanceBuilder> instanceBuilder();

    public Class<? extends Filler> filler() default NoFiller.class;
}
