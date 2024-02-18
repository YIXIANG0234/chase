package edu.hhuc.yixiang.common.enums;

import lombok.Getter;

/**
 * @author guwanghuai
 * @version 1.0
 * @project chase
 * @description
 * @date 2024/1/8 17:46:31
 */
@Getter
public enum LimitExecutorEnum {
    GUAVA("guava", "guava限流器"),
    FIXED("fixed", "固定窗口限流"),
    SLIDE("slide", "滑动窗口限流"),
    LEAKY("leaky", "漏桶限流"),
    TOKEN("token", "令牌桶限流"),
    ;
    private final String code;
    private final String message;

    LimitExecutorEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
