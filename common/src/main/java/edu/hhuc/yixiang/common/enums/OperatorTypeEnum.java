package edu.hhuc.yixiang.common.enums;

import lombok.Getter;

/**
 * @author guwanghuai
 * @version 1.0
 * @project chase
 * @description 操作日志操作类型
 * @date 2023/12/28 16:38:53
 */
@Getter
public enum OperatorTypeEnum {
    ADD("add", "新增"),
    DELETE("delete", "删除"),
    UPDATE("update", "修改"),
    SELECT("select", "查询"),
    EXECUTE("execute", "执行");

    OperatorTypeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    private final String code;
    private final String message;
}
