package org.zx.queryex.jpa.resolve;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.zx.queryex.base.api.QEMatcherType;

/**
 * @author zhouxin
 * @since 2019/8/23
 */
@Component
public class QEJPAMatcherNotEqualResolve extends QEJPAAbstractMatcherResolve {

    @Override
    public QEMatcherType matcherType() {
        return QEMatcherType.notEqual;
    }

    @Override
    public Specification apply(String fieldName, Object fieldValue) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.notEqual(this.getPath(root, fieldName), fieldValue);
    }
}
