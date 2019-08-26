package org.zx.queryex.base.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author zhouxin
 * @since 2019/6/6
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QEPageOrder {

    // 排序的属性名
    private String property;

    // 排序方向
    private Direction direction = Direction.ASC;

    public enum Direction{
        ASC, DESC;
    }
}
