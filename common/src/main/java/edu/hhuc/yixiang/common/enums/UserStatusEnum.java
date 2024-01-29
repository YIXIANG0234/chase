package edu.hhuc.yixiang.common.enums;

import lombok.Getter;

/**
 * @author guwanghuai
 * @version 1.0
 * @project chase
 * @description
 * @date 2024/1/21 21:47:16
 */
@Getter
public enum UserStatusEnum {
    ACTIVATE("activate", "启用"),
    FORBIDDEN("forbidden", "禁用");
    private final String code;
    private final String message;

    UserStatusEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
