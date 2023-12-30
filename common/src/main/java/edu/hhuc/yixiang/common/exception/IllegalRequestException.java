package edu.hhuc.yixiang.common.exception;

/**
 * @author guwanghuai
 * @version 1.0
 * @project chase
 * @description
 * @date 2023/12/30 15:20:11
 */
public class IllegalRequestException extends ChaseBaseException {
    @Override
    public Integer getErrorCode() {
        return super.getErrorCode();
    }

    public IllegalRequestException(String message) {
        super(message);
    }

    public IllegalRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalRequestException(Throwable cause) {
        super(cause);
    }

    public IllegalRequestException(String message, Integer errorCode) {
        super(message, errorCode);
    }

    public IllegalRequestException(String message, Throwable cause, Integer errorCode) {
        super(message, cause, errorCode);
    }

    public IllegalRequestException(Throwable cause, Integer errorCode) {
        super(cause, errorCode);
    }
}
