package edu.hhuc.yixiang.common.enums;

import lombok.Getter;

/**
 * @author guwanghuai
 * @version 1.0
 * @project chase
 * @description 操作日志操作模块
 * @date 2023/12/28 16:39:16
 */
@Getter
public enum OperatorModuleEnum {
    SYSTEM("system", "系统操作")
    ;

    OperatorModuleEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    private String code;
    private String message;
}
