package org.zx.queryex.base.api;

import java.util.function.BiFunction;

/**
 * @author zhouxin
 * @since 2019/8/23
 */
public interface QEMatcherTypeResolve<T> extends BiFunction<String, Object, T> {

    QEMatcherType matcherType();
}
