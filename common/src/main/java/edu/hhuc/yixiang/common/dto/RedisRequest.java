package edu.hhuc.yixiang.common.dto;

import lombok.Data;

/**
 * @author guwanghuai
 * @version 1.0
 * @project chase
 * @description
 * @date 2023/12/26 21:14:51
 */
@Data
public class RedisRequest {
    private String key;
    private String value;
    private Long expireSeconds;
}
