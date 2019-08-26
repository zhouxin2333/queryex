package org.zx.queryex.jpa;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.zx.queryex.base.api.QEAbstractMatcherFieldHandler;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * @author zhouxin
 * @since 2019/8/23
 */
@Component
public class QEJPAMatcherFieldHandler extends QEAbstractMatcherFieldHandler<Specification, Specification> {

    @Override
    public Specification empty() {
        return (root, query, criteriaBuilder) -> criteriaBuilder.and();
    }

    @Override
    public Optional<Specification> reduce(Stream<Specification> stream) {
        return stream.reduce(Specification::and);
    }
}
