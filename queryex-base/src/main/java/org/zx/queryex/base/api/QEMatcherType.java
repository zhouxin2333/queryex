package org.zx.queryex.base.api;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author zhouxin
 * @since 2019/6/5
 */
@Getter
@AllArgsConstructor
public enum QEMatcherType {

    // filed = value
    equal,

    // field != value
    notEqual,

    // field like value
    like,

    // field not like value
    notLike,

    // filed > value
    gt,

    // field >= value
    ge,

    // field < value
    lt,

    // field <= value
    le,
    ;
}
