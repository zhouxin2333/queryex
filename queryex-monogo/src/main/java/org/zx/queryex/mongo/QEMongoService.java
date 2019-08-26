package org.zx.queryex.mongo;

import org.springframework.data.mongodb.core.query.Query;
import org.zx.queryex.base.api.QEService;

/**
 * @author zhouxin
 * @since 2019/8/23
 */
public interface QEMongoService<T, ID> extends QEService<T, ID, Query> {

}
