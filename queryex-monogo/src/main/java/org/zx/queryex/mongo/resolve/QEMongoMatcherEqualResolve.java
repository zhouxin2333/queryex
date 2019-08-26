package org.zx.queryex.mongo.resolve;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.CriteriaDefinition;
import org.springframework.stereotype.Component;
import org.zx.queryex.base.api.QEMatcherType;

import java.util.function.BiFunction;

/**
 * @author zhouxin
 * @since 2019/8/26
 */
@Component
public class QEMongoMatcherEqualResolve extends QEMongoAbstractMatcherResolve {

    private static BiFunction<String, Object, Criteria> equalBiFun = (fieldName, fieldValue) -> Criteria.where(fieldName).is(fieldValue);

    @Override
    public QEMatcherType matcherType() {
        return QEMatcherType.equal;
    }

    @Override
    public CriteriaDefinition apply(String fieldName, Object fieldValue) {
        return this.buildCriteriaDefinition(fieldName, fieldValue, equalBiFun);
    }
}
