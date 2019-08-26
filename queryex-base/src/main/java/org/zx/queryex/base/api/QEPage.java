package org.zx.queryex.base.api;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author zhouxin
 * @since 2019/6/5
 */
@Getter
@Setter
@NoArgsConstructor
public class QEPage<T> {
    // 当前页索引
    private Integer pageIndex;
    // 每一页有多少数据
    private Integer pageSize;
    // 一共有多少页
    private Integer totalPages;
    // 一共有多少数据
    private Long totalElements;

    private List<T> data;

    public static <T> QEPage<T> of(Page<T> page){
        QEPage<T> QEPage = new QEPage();

        int pageIndex = page.getNumber() + 1;
        QEPage.setPageIndex(pageIndex);

        int pageSize = page.getSize();
        QEPage.setPageSize(pageSize);

        int totalPages = page.getTotalPages();
        QEPage.setTotalPages(totalPages);

        long totalElements = page.getTotalElements();
        QEPage.setTotalElements(totalElements);

        List<T> data = page.getContent();
        QEPage.setData(data);

        return QEPage;
    }
}
