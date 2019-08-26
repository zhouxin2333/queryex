package org.zx.queryex.base.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author zhouxin
 * @since 2019/8/23
 */
public abstract class QEAbstractService<T, ID, Q, Z> implements QEService<T, ID, Q> {

    @Autowired
    private QEMatcherFieldHandler<Q, Z> fieldHandler;

    @Override
    public Optional<T> queryOne(Object query) {
        Q q = this.buildQueryObj(query);
        return this.findOne(q);
    }

    @Override
    public List<T> queryAll(Object query) {
        Q q = this.buildQueryObj(query);
        return this.findAll(q);
    }

    @Override
    public <R extends QEPageQuery> QEPage<T> findPage(R query) {
        Q q = this.buildQueryObj(query);
        Pageable pageable = this.toPageable(query);
        return this.findPage(q, pageable);
    }

    @Override
    public long queryCount(Object query) {
        Q q = this.buildQueryObj(query);
        return this.count(q);
    }

    private Q buildQueryObj(Object query) {
        Field[] allMatcherFields = QEMatcherFieldContainer.getAllMatcherFields(query);
        if (QEUtils.isEmpty(allMatcherFields)) return this.fieldHandler.empty();

        Stream<Z> zStream = Arrays.stream(allMatcherFields)
                                  .map(field -> this.fieldToQueryObj(field, query))
                                  .filter(Objects::nonNull);
        Optional<Q> optionalQ = this.fieldHandler.reduce(zStream);

        return optionalQ.orElse(this.fieldHandler.empty());
    }

    private Z fieldToQueryObj(Field field, Object query) {
        // 获取当前属性值
        String fieldName = field.getName();
        Object fieldValue = QEUtils.getValueWithoutError(query, fieldName);
        // 若没有属性值，则直接返回null
        if (Objects.isNull(fieldValue)) return null;

        QEMatcherField annotation = field.getAnnotation(QEMatcherField.class);
        QEMatcherType operator = annotation.matcherType();
        String annotationName = annotation.name();
        if (QEUtils.isNotEmpty(annotationName)){
            fieldName = annotationName;
        }

        QEMatcherTypeResolve<Z> resolve = this.fieldHandler.apply(operator);
        Z apply = resolve.apply(fieldName, fieldValue);
        return apply;
    }

    public Pageable toPageable(QEPageQuery query){
        PageRequest request = null;
        List<QEPageOrder> orders = query.getOrders();
        int pageIndex = query.getPageIndex() - 1;
        if (QEUtils.isEmpty(orders)){
            request = PageRequest.of(pageIndex, query.getPageSize());
        }else {
            List<Sort.Order> sortOrders = orders.stream().map(this::toOrder).collect(Collectors.toList());
            Sort sort = Sort.by(sortOrders);
            request = PageRequest.of(pageIndex, query.getPageSize(), sort);
        }
        return request;
    }

    private Sort.Order toOrder(QEPageOrder stPageOrder) {
        String property = stPageOrder.getProperty();
        QEPageOrder.Direction direction = stPageOrder.getDirection();
        if (QEPageOrder.Direction.ASC.equals(direction)){
            return Sort.Order.asc(property);
        }else {
            return Sort.Order.desc(property);
        }
    }
}
