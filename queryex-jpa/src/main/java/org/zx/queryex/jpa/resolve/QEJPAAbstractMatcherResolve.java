package org.zx.queryex.jpa.resolve;

import org.springframework.data.jpa.domain.Specification;
import org.zx.queryex.base.api.QEConstants;
import org.zx.queryex.base.api.QEMatcherTypeResolve;
import org.zx.queryex.base.api.QEUtils;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author zhouxin
 * @since 2019/8/23
 */
public abstract class QEJPAAbstractMatcherResolve implements QEMatcherTypeResolve<Specification> {

    private static final Pattern pattern = Pattern.compile("\\.");
    protected Path getPath(Root root, String fieldName){
        if (fieldName.contains("\\.")){
            List<String> fieldNames = pattern.splitAsStream(fieldName).collect(Collectors.toList());

            String firstFieldName = QEUtils.findFirst(fieldNames);
            Join joinTable = fieldNames.stream()
                                       .skip(1)
                                       .limit(fieldNames.size() - 2)
                                       .reduce(root.join(firstFieldName, JoinType.LEFT),
                                                (join, s) -> join.join(s, JoinType.LEFT),
                                                QEConstants.getLeftBinaryOperator);
            String lastFieldName = QEUtils.findLast(fieldNames);
            return joinTable.get(lastFieldName);
        }else {
            return root.get(fieldName);
        }
    }
}
