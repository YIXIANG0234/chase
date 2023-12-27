package edu.hhuc.yixiang.common.annotation;

import java.lang.annotation.*;

/**
 * @author guwanghuai
 * @version 1.0
 * @project chase
 * @description 分布式锁实现
 * @date 2023/12/26 21:22:51
 */
@Target(value = ElementType.METHOD)
@Retention(value = RetentionPolicy.RUNTIME)
@Documented
public @interface DistributedLock {
    // 锁
    String key();
    // 是否重试
    boolean retry() default false;
}
