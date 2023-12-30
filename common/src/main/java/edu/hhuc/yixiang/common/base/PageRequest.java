package edu.hhuc.yixiang.common.base;

import lombok.Data;

/**
 * 分页查询组件
 * @author guwanghuai
 * @version 1.0
 * @project chase
 * @description
 * @date 2023/12/30 14:58:39
 */
@Data
public class PageRequest<T> {
    /**
     * 当前页，从1开始
     */
    private Integer page;
    /**
     * 每页数量
     */
    private Integer pageSize;
    /**
     * 过滤器
     */
    private T filter;
}
