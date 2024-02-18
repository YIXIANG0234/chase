package edu.hhuc.yixiang.common.constant;

/**
 * @author guwanghuai
 * @version 1.0
 * @project chase
 * @description
 * @date 2023/12/26 16:52:29
 */
public interface RedisConstants {
    String DISTRIBUTED_LOCK_PREFIX = "chase:distributed:lock:";
    Long DISTRIBUTED_LOCK_DEFAULT_TIMEOUT = 5000L;
    Long DISTRIBUTED_LOCK_TRY_TIMEOUT = 2000L;
    String LIMITER_PREFIX = "chase:limiter:%s:%s:%s_%s";
}
