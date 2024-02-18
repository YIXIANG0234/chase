package edu.hhuc.yixiang.common.annotation;

import edu.hhuc.yixiang.common.enums.LimitExecutorEnum;
import edu.hhuc.yixiang.common.enums.LimiteDimensionEnum;

import java.lang.annotation.*;

/**
 * @author guwanghuai
 * @version 1.0
 * @project chase
 * @description
 * @date 2024/1/8 17:26:19
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
@Inherited
public @interface Limiter {
    /**
     * @return 限流的时间窗口，单位为毫秒
     */
    int window() default 1000;

    /**
     * @return 限流阈值
     */
    int threshold() default 5;

    /**
     * @return 每秒产生的令牌数
     */
    int rate() default 5;

    /**
     * @return 限流维度，默认限流维度是接口的总请求量
     */
    LimiteDimensionEnum dimension() default LimiteDimensionEnum.SERVICE;

    /**
     * @return 使用的限流算法
     */
    LimitExecutorEnum executor() default LimitExecutorEnum.FIXED;
}
