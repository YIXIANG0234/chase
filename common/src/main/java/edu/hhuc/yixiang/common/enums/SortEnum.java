package edu.hhuc.yixiang.common.enums;

import lombok.Getter;

/**
 * @author guwanghuai
 * @version 1.0
 * @project chase
 * @description
 * @date 2023/12/30 15:06:45
 */
@Getter
public enum SortEnum {
    ASC("asc", "升序"),
    DESC("desc", "降序")
    ;

    private String code;
    private String message;

    SortEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
