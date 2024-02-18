package edu.hhuc.yixiang.common.enums;

import lombok.Getter;

/**
 * 限流的维度
 *
 * @author guwanghuai
 * @version 1.0
 * @project chase
 * @description
 * @date 2024/1/8 17:42:26
 */
@Getter
public enum LimiteDimensionEnum {
    USER("user", "用户"),
    IP("ip", "ip"),
    SERVICE("service", "接口总请求");

    private final String code;
    private final String message;

    LimiteDimensionEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
