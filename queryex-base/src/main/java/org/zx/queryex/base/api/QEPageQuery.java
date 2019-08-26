package org.zx.queryex.base.api;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhouxin
 * @since 2019/8/22
 */
@Getter
@Setter
@NoArgsConstructor
public class QEPageQuery implements Serializable {

    private static final long serialVersionUID = -1076794983061449128L;

    // 当前页索引
    private Integer pageIndex = Integer.valueOf(1);

    // 每页数据容量
    private Integer pageSize = Integer.valueOf(10);

    // 排序方式
    private List<QEPageOrder> orders = new ArrayList<>();

    public <T extends QEPageQuery> void transfer(T original){
        this.setPageIndex(original.getPageIndex());
        this.setPageSize(original.getPageSize());
        this.setOrders(original.getOrders());
    }

    public QEPageQuery sortByAsc(String property){
        QEPageOrder order = new QEPageOrder(property, QEPageOrder.Direction.ASC);
        this.addOrder(order);
        return this;
    }

    public QEPageQuery sortByDesc(String property){
        QEPageOrder order = new QEPageOrder(property, QEPageOrder.Direction.DESC);
        this.addOrder(order);
        return this;
    }

    private void addOrder(QEPageOrder order){
        this.orders.add(order);
    }
}
