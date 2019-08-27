package org.zx.queryex.base.api;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ResolvableType;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author zhouxin
 * @since 2019/8/23
 */
public abstract class QEAbstractMatcherFieldHandler<T, R> implements QEMatcherFieldHandler<T, R>, InitializingBean {

    @Autowired
    private List<QEMatcherTypeResolve<R>> allResolves;

    private List<QEMatcherTypeResolve<R>> resolves;

    @Override
    public QEMatcherTypeResolve<R> apply(QEMatcherType type) {
        Optional<QEMatcherTypeResolve<R>> resolveOptional = resolves.stream().filter(resolve -> resolve.matcherType().equals(type)).findFirst();
        return resolveOptional.get();
    }

    @Override
    public void afterPropertiesSet() {
        resolves = allResolves.stream().filter(this::filterResolve).collect(Collectors.toList());
    }

    private boolean filterResolve(QEMatcherTypeResolve<R> resolve) {
        Class<?> handleResolveGenericClass = ResolvableType.forClass(this.getClass()).getSuperType().getGeneric(1).resolve();
        Class<?> resolveGenericClass = ResolvableType.forClass(resolve.getClass()).getSuperType().getInterfaces()[0].getGeneric(0).resolve();
        return handleResolveGenericClass.equals(resolveGenericClass);
    }
}
