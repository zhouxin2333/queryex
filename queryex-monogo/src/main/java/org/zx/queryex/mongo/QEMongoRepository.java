package org.zx.queryex.mongo;

/**
 * @author zhouxin
 * @since 2019/6/5
 */

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface QEMongoRepository<T, ID> extends MongoRepository<T, ID> {
}
