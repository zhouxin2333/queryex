package org.zx.queryex.mongo;

import org.springframework.data.mongodb.core.query.CriteriaDefinition;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import org.zx.queryex.base.api.QEAbstractMatcherFieldHandler;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * @author zhouxin
 * @since 2019/8/26
 */
@Component
public class QEMongoMatcherFieldHandler extends QEAbstractMatcherFieldHandler<Query, CriteriaDefinition> {

    @Override
    public Query empty() {
        return new Query();
    }

    @Override
    public Optional<Query> reduce(Stream<CriteriaDefinition> stream) {
        Query reduce = stream.reduce(new Query(), (query, criteriaDefinition) -> query.addCriteria(criteriaDefinition), (query, query2) -> query);
        return Optional.ofNullable(reduce);
    }
}
