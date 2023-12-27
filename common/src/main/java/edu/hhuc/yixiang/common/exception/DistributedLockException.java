package edu.hhuc.yixiang.common.exception;

/**
 * @author guwanghuai
 * @version 1.0
 * @project chase
 * @description
 * @date 2023/12/26 22:17:22
 */
public class DistributedLockException extends ChaseBaseException{
    public DistributedLockException(String message) {
        super(message);
    }

    public DistributedLockException(String message, Throwable cause) {
        super(message, cause);
    }

    public DistributedLockException(Throwable cause) {
        super(cause);
    }

    public DistributedLockException(String message, Integer errorCode) {
        super(message, errorCode);
    }

    public DistributedLockException(String message, Throwable cause, Integer errorCode) {
        super(message, cause, errorCode);
    }

    public DistributedLockException(Throwable cause, Integer errorCode) {
        super(cause, errorCode);
    }
}
