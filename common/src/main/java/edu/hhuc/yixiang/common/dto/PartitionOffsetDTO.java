package edu.hhuc.yixiang.common.dto;

import lombok.Data;

/**
 * @author guwanghuai
 * @version 1.0
 * @project chase
 * @description
 * @date 2024/11/28 14:14:53
 */
@Data
public class PartitionOffsetDTO {
    /**
     * 需要重置offset的分区
     */
    private Integer partition;
    /**
     * 需要设置的offset的值
     */
    private Long offset;
}
