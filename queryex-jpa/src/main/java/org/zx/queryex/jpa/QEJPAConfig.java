package org.zx.queryex.jpa;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author zhouxin
 * @since 2019/8/26
 */
@Configuration
@ComponentScan("org.zx.queryex.jpa.resolve")
@Import(QEJPAMatcherFieldHandler.class)
public class QEJPAConfig {
}
