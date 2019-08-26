package org.zx.queryex.base.api;

import java.util.function.BinaryOperator;

/**
 * @author zhouxin
 * @since 2019/8/23
 */
public interface QEConstants {

    BinaryOperator getLeftBinaryOperator = (left, right) -> left;
    BinaryOperator getRightBinaryOperator = (left, right) -> right;
}
