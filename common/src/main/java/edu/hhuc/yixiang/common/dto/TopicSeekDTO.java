package edu.hhuc.yixiang.common.dto;

import lombok.Data;

import java.util.List;

/**
 * @author guwanghuai
 * @version 1.0
 * @project chase
 * @description
 * @date 2024/11/28 14:07:29
 */
@Data
public class TopicSeekDTO {
    /**
     * 消息topic
     */
    private String topic;
    /**
     * 需要重置offset的分区
     */
    private List<PartitionOffsetDTO> partitions;
    /**
     * 本次请求是否重置offset
     */
    private Boolean reset;
}
