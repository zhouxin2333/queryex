package org.zx.queryex.base.api;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author zhouxin
 * @since 2019/6/5
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface QEMatcherField {

    String name() default "";

    QEMatcherType matcherType() default QEMatcherType.equal;
}
