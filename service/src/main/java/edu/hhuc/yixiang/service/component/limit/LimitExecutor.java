package edu.hhuc.yixiang.service.component.limit;

import edu.hhuc.yixiang.common.enums.LimitExecutorEnum;

/**
 * 限流器接口
 * @author guwanghuai
 * @version 1.0
 * @project chase
 * @description
 * @date 2024/1/8 21:40:55
 */
public interface LimitExecutor {

    LimitExecutorEnum getExecutorType();
    /**
     * @return 判断是否限流的方法
     */
    boolean acquire(RateLimitConfiguration configuration);
}
