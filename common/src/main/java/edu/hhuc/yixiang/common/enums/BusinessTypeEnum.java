package edu.hhuc.yixiang.common.enums;

import lombok.Getter;

/**
 * @author guwanghuai
 * @version 1.0
 * @project chase
 * @description
 * @date 2024/1/3 20:57:47
 */
@Getter
public enum BusinessTypeEnum {
    SYSTEM("system", "系统自增id")
    ;


    BusinessTypeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    private final String code;
    private final String message;
}
