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
public class QEMongoMatcherLikeResolve extends QEMongoAbstractMatcherResolve {

    private static final String likePatternStr = "^.*%s.*$";
    private static BiFunction<String, Object, Criteria> likeBiFun = (fieldName, fieldValue) -> Criteria.where(fieldName).regex(String.format(likePatternStr, fieldValue));

    @Override
    public QEMatcherType matcherType() {
        return QEMatcherType.like;
    }

    @Override
    public CriteriaDefinition apply(String fieldName, Object fieldValue) {
        return this.buildCriteriaDefinition(fieldName, fieldValue, likeBiFun);
    }
}
