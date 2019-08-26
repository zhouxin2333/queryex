package org.zx.queryex.jpa.resolve;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.zx.queryex.base.api.QEMatcherType;

/**
 * @author zhouxin
 * @since 2019/8/23
 */
@Component
public class QEJPAMatcherEqualResolve extends QEJPAAbstractMatcherResolve {

    @Override
    public QEMatcherType matcherType() {
        return QEMatcherType.equal;
    }

    @Override
    public Specification apply(String fieldName, Object fieldValue) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(this.getPath(root, fieldName), fieldValue);
    }
}
