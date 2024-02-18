package edu.hhuc.yixiang.service.component.limit;

import lombok.Data;

/**
 * @author guwanghuai
 * @version 1.0
 * @project chase
 * @description
 * @date 2024/1/8 21:53:22
 */
@Data
public class RateLimitConfiguration {
    private String key;
    private Integer threshold;
    /**
     * 固定窗口和滑动窗口的时间范围
     */
    private Integer window;

    /**
     * 每秒产生多少个令牌
     */
    private Integer rate;

    public RateLimitConfiguration(String key, Integer threshold, Integer window, Integer rate) {
        this.key = key;
        this.threshold = threshold;
        this.window = window;
        this.rate = rate;
    }
}
