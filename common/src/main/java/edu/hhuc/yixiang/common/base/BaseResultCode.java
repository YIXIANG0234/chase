package edu.hhuc.yixiang.common.base;

import lombok.Getter;

/**
 * @author guwanghuai
 * @version 1.0
 * @project chase
 * @description
 * @date 2023/12/27 20:50:23
 */
@Getter
public class BaseResultCode {
    public static BaseResultCode ERROR = of(-1, "Request failed");
    public static BaseResultCode SUCCESS = of(0, "success");

    public static BaseResultCode INTERNAL_SERVER_ERROR = of(1, "系统出错啦！请稍后重试");
    public static BaseResultCode REQUEST_INVALID_ERROR = of(2, "请求参数异常，请检查");

    private final Integer resultCode;
    private final String resultMessage;

    public static BaseResultCode of(Integer resultCode, String resultMessage) {
        return new BaseResultCode(resultCode, resultMessage);
    }

    private BaseResultCode(Integer resultCode, String resultMessage) {
        this.resultCode = resultCode;
        this.resultMessage = resultMessage;
    }
}
