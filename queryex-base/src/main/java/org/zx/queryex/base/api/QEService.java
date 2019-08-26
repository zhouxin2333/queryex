package org.zx.queryex.base.api;

import org.springframework.data.domain.Pageable;
import org.springframework.lang.Nullable;

import java.util.List;
import java.util.Optional;

/**
 * 查询扩展接口基础Service类
 * @author zhouxin
 * @since 2019/8/22
 */
public interface QEService<T, ID, Q> extends QEObjService<T> {

    Optional<T> saveOrUpdate(T t);

    Optional<T> findById(ID id);

    Optional<T> findOne(@Nullable Q query);

    List<T> findAll(@Nullable Q query);

    <R extends QEPageQuery> QEPage<T> findPage(@Nullable R query);

    <R extends QEPageQuery> QEPage<T> findPage(Q query, Pageable pageable);

    long count(@Nullable Q query);

    default boolean isExist(@Nullable Q query){
        Long count = this.count(query);
        return count != null && count.longValue() > 0;
    }

    default boolean isNotExist(@Nullable Q query){
        return !this.isExist(query);
    }
}
