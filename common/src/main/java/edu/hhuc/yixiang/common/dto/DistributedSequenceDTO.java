package edu.hhuc.yixiang.common.dto;

import lombok.Data;

import java.math.BigInteger;

/**
 * @author guwanghuai
 * @version 1.0
 * @project chase
 * @description
 * @date 2024/1/3 21:00:05
 */
@Data
public class DistributedSequenceDTO {
    /**
     * id
     */
    private BigInteger id;

    /**
     * id的业务分类
     */
    private String businessType;

    /**
     * 当前业务可获取的最大id
     */
    private Long maxId;

    /**
     * 每次获取的步长
     */
    private Integer step;

    /**
     * 业务类型描述
     */
    private String remark;
}
