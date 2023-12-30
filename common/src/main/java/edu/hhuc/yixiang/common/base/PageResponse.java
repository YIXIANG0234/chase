package edu.hhuc.yixiang.common.base;

import lombok.Data;

import java.util.List;

/**
 * @author guwanghuai
 * @version 1.0
 * @project chase
 * @description
 * @date 2023/12/30 15:05:48
 */
@Data
public class PageResponse<T> {
    /**
     * 当前页数据
     */
    private List<T> list;
    /**
     * 总页数
     */
    private Long totalPage;
    /**
     * 数据总数
     */
    private Long totalRow;

    public static <T> PageResponse<T> of(List<T> list, Long totalPage, Long totalRow) {
        PageResponse<T> response = new PageResponse<>();
        response.setList(list);
        response.setTotalPage(totalPage);
        response.setTotalRow(totalRow);
        return response;
    }

}
