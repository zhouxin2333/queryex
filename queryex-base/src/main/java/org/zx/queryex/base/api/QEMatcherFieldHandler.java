package org.zx.queryex.base.api;

import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * @author zhouxin
 * @since 2019/8/23
 */
public interface QEMatcherFieldHandler<T, R> extends Function<QEMatcherType, QEMatcherTypeResolve<R>> {

    T empty();

    Optional<T> reduce(Stream<R> stream);
}
