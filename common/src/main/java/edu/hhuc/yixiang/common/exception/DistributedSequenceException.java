package edu.hhuc.yixiang.common.exception;

import edu.hhuc.yixiang.common.base.BaseResultCode;

/**
 * @author guwanghuai
 * @version 1.0
 * @project chase
 * @description
 * @date 2024/1/4 17:26:21
 */
public class DistributedSequenceException extends ChaseBaseException {
    @Override
    public Integer getErrorCode() {
        return super.getErrorCode();
    }

    public DistributedSequenceException(String message) {
        super(message);
    }

    public DistributedSequenceException(String message, Throwable cause) {
        super(message, cause);
    }

    public DistributedSequenceException(Throwable cause) {
        super(cause);
    }

    public DistributedSequenceException(String message, Integer errorCode) {
        super(message, errorCode);
    }

    public DistributedSequenceException(String message, Throwable cause, Integer errorCode) {
        super(message, cause, errorCode);
    }

    public DistributedSequenceException(Throwable cause, Integer errorCode) {
        super(cause, errorCode);
    }

    public DistributedSequenceException(BaseResultCode resultCode) {
        super(resultCode);
    }
}
