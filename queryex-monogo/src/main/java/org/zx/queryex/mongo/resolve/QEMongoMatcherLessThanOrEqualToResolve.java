package org.zx.queryex.mongo.resolve;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.CriteriaDefinition;
import org.springframework.stereotype.Component;
import org.zx.queryex.base.api.QEMatcherType;

/**
 * @author zhouxin
 * @since 2019/8/26
 */
@Component
public class QEMongoMatcherLessThanOrEqualToResolve extends QEMongoAbstractMatcherResolve {


    @Override
    public QEMatcherType matcherType() {
        return QEMatcherType.le;
    }

    @Override
    public CriteriaDefinition apply(String fieldName, Object fieldValue) {
        return Criteria.where(fieldName).lte(fieldValue);
    }
}
