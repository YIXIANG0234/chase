package edu.hhuc.yixiang.common.exception;

import edu.hhuc.yixiang.common.base.BaseResultCode;
import lombok.Getter;

/**
 * @author guwanghuai
 * @version 1.0
 * @project chase
 * @description
 * @date 2023/12/27 21:03:05
 */
@Getter
public class ChaseBaseException extends RuntimeException {
    private final Integer errorCode;

    public ChaseBaseException(String message) {
        this(message, BaseResultCode.ERROR.getResultCode());
    }

    public ChaseBaseException(String message, Throwable cause) {
        this(message, cause, BaseResultCode.ERROR.getResultCode());
    }

    public ChaseBaseException(Throwable cause) {
        this(cause, BaseResultCode.ERROR.getResultCode());
    }

    public ChaseBaseException(String message, Integer errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public ChaseBaseException(String message, Throwable cause, Integer errorCode) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public ChaseBaseException(Throwable cause, Integer errorCode) {
        super(cause);
        this.errorCode = errorCode;
    }
}
