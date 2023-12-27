package edu.hhuc.yixiang.common.base;

import lombok.Getter;

import java.io.Serializable;

/**
 * @author guwanghuai
 * @version 1.0
 * @project chase
 * @description
 * @date 2023/12/27 20:28:58
 */

@Getter
public class BaseResponse<T> implements Serializable {
    private final Integer resultCode;
    private final String resultMessage;
    private final Boolean success;
    private T result;

    private BaseResponse(Integer resultCode, String resultMessage, Boolean success) {
        this.resultCode = resultCode;
        this.resultMessage = resultMessage;
        this.success = success;
    }

    private BaseResponse(Integer resultCode, String resultMessage, Boolean success, T result) {
        this(resultCode, resultMessage, success);
        this.result = result;
    }

    public static <T> BaseResponse<T> ofSuccess() {
        return new BaseResponse<>(BaseResultCode.SUCCESS.getResultCode(), BaseResultCode.SUCCESS.getResultMessage(), true);
    }

    public static <T> BaseResponse<T> ofSuccess(T result) {
        return new BaseResponse<>(BaseResultCode.SUCCESS.getResultCode(), BaseResultCode.SUCCESS.getResultMessage(), true, result);
    }

    public static <T> BaseResponse<T> ofFailure() {
        return ofFailure(BaseResultCode.ERROR);
    }

    public static <T> BaseResponse<T> ofFailure(Integer resultCode, String resultMessage) {
        return new BaseResponse<>(resultCode, resultMessage, false);
    }

    public static <T> BaseResponse<T> ofFailure(BaseResultCode baseResultCode) {
        return new BaseResponse<>(baseResultCode.getResultCode(), baseResultCode.getResultMessage(), false);
    }

}
