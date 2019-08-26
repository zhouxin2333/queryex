package org.zx.queryex.mongo;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author zhouxin
 * @since 2019/8/26
 */
@Configuration
@ComponentScan("org.zx.queryex.mongo.resolve")
@Import(QEMongoMatcherFieldHandler.class)
public class QEMongoConfig {
}
