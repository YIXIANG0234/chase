package edu.hhuc.yixiang.common.base;

import lombok.Data;

/**
 * 带排序待分页查询组件
 * @author guwanghuai
 * @version 1.0
 * @project chase
 * @description
 * @date 2023/12/30 15:00:13
 */
@Data
public class SortRequest<T> extends PageRequest<T> {
    private Sorter sorter;
}
