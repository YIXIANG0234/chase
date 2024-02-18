package edu.hhuc.yixiang.common.exception;

import edu.hhuc.yixiang.common.base.BaseResultCode;

/**
 * @author guwanghuai
 * @version 1.0
 * @project chase
 * @description
 * @date 2024/1/8 20:47:43
 */
public class RateLimitException extends ChaseBaseException {

    public RateLimitException(String message) {
        super(message);
    }

    public RateLimitException(String message, Throwable cause) {
        super(message, cause);
    }

    public RateLimitException(Throwable cause) {
        super(cause);
    }

    public RateLimitException(String message, Integer errorCode) {
        super(message, errorCode);
    }

    public RateLimitException(String message, Throwable cause, Integer errorCode) {
        super(message, cause, errorCode);
    }

    public RateLimitException(Throwable cause, Integer errorCode) {
        super(cause, errorCode);
    }

    public RateLimitException(BaseResultCode resultCode) {
        super(resultCode);
    }
}
