package org.zx.queryex.mongo.resolve;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.CriteriaDefinition;
import org.zx.queryex.base.api.QEMatcherTypeResolve;

import java.util.List;
import java.util.function.BiFunction;

/**
 * @author zhouxin
 * @since 2019/8/26
 */
public abstract class QEMongoAbstractMatcherResolve implements QEMatcherTypeResolve<CriteriaDefinition> {

    protected CriteriaDefinition buildCriteriaDefinition(String fieldName, Object fieldValue, BiFunction<String, Object, Criteria> function){
        if (fieldValue instanceof List){
            List<?> list = (List) fieldValue;
            Criteria[] criteriaArray = list.stream().map(d -> function.apply(fieldName, d)).toArray(Criteria[]::new);
            Criteria criteria = new Criteria().orOperator(criteriaArray);
            return criteria;
        }else {
            return function.apply(fieldName, fieldValue);
        }
    }
}
