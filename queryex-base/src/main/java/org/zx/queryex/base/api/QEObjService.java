package org.zx.queryex.base.api;

import org.springframework.lang.Nullable;

import java.util.List;
import java.util.Optional;

/**
 * 查询扩展接口基础Service类
 * @author zhouxin
 * @since 2019/8/22
 */
public interface QEObjService<T> {

    Optional<T> queryOne(@Nullable Object query);

    List<T> queryAll(@Nullable Object query);

    long queryCount(@Nullable Object query);

    default boolean queryIsExist(@Nullable Object query){
        Long count = this.queryCount(query);
        return count != null && count.longValue() > 0;
    }

    default boolean queryIsNotExist(@Nullable Object query){
        return !this.queryIsExist(query);
    }
}
