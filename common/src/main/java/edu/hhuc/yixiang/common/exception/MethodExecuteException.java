package edu.hhuc.yixiang.common.exception;

import edu.hhuc.yixiang.common.base.BaseResultCode;

/**
 * @author guwanghuai
 * @version 1.0
 * @project chase
 * @description
 * @date 2024/1/10 14:02:56
 */
public class MethodExecuteException extends ChaseBaseException {
    public MethodExecuteException(String message) {
        super(message);
    }

    public MethodExecuteException(String message, Throwable cause) {
        super(message, cause);
    }

    public MethodExecuteException(Throwable cause) {
        super(cause);
    }

    public MethodExecuteException(String message, Integer errorCode) {
        super(message, errorCode);
    }

    public MethodExecuteException(String message, Throwable cause, Integer errorCode) {
        super(message, cause, errorCode);
    }

    public MethodExecuteException(Throwable cause, Integer errorCode) {
        super(cause, errorCode);
    }

    public MethodExecuteException(BaseResultCode resultCode) {
        super(resultCode);
    }
}
