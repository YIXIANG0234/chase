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
    public static BaseResultCode DISTRIBUTED_SEQUENCE_EXCEPTION = of(3, "分布式id初始化中或不存在请求类型的id，请稍后重试");
    public static BaseResultCode RATE_LIMIT_EXCEPTION = of(4, "服务访问已达到最大阈值，请稍后重试！");
    public static BaseResultCode METHOD_EXECUTE_EXCEPTION = of(5, "方法执行异常");
    public static BaseResultCode ACCESS_DENIED_EXCEPTION = of(6, "您无权限执行该操作");

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
