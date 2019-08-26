package org.zx.queryex.jpa;

import org.springframework.data.jpa.domain.Specification;
import org.zx.queryex.base.api.QEService;

/**
 * @author zhouxin
 * @since 2019/8/23
 */
public interface QEJPAService<T, ID> extends QEService<T, ID, Specification<T>> {

}
