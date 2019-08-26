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
public class QEMongoMatcherNotEqualResolve extends QEMongoAbstractMatcherResolve {

    private static BiFunction<String, Object, Criteria> notEqualBiFun = (fieldName, fieldValue) -> Criteria.where(fieldName).ne(fieldValue);

    @Override
    public QEMatcherType matcherType() {
        return QEMatcherType.notEqual;
    }

    @Override
    public CriteriaDefinition apply(String fieldName, Object fieldValue) {
        return this.buildCriteriaDefinition(fieldName, fieldValue, notEqualBiFun);
    }
}
