package edu.hhuc.yixiang.common.enums;

import lombok.Getter;

/**
 * @author guwanghuai
 * @version 1.0
 * @project chase
 * @description
 * @date 2024/1/10 22:01:21
 */
@Getter
public enum ScriptEnum {
    DISTRIBUTED_LOCK_RELEASE_SCRIPT("distributedLockReleaseScript", "分布式锁释放脚本", "distributedLockReleaseScript.lua", Long.class),
    FIXED_LIMIT_SCRIPT("fixedLimitScript", "固定窗口限流脚本", "fixedLimitScript.lua", Long.class),
    SLIDE_LIMIT_SCRIPT("slideLimitScript", "滑动窗口限流脚本", "slideLimitScript.lua", Long.class),
    TOKEN_BUCKET_LIMIT_SCRIPT("tokenBucketLimitScript", "令牌桶限流脚本", "tokenBucketLimitScript.lua", Long.class),
    LEAKY_BUCKET_LIMIT_SCRIPT("leakyBucketLimitScript", "漏桶限流脚本", "leakyBucketLimitScript.lua", Long.class),
    ;
    private final String code;
    private final String message;
    private final String fileName;
    private final Class<?> resultType;

    ScriptEnum(String code, String message, String fileName, Class<?> resultType) {
        this.code = code;
        this.message = message;
        this.fileName = fileName;
        this.resultType = resultType;
    }
}
