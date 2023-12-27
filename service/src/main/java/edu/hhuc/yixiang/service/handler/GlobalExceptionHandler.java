package edu.hhuc.yixiang.service.handler;

import edu.hhuc.yixiang.common.base.BaseResponse;
import edu.hhuc.yixiang.common.base.BaseResultCode;
import edu.hhuc.yixiang.common.exception.ChaseBaseException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author guwanghuai
 * @version 1.0
 * @project chase
 * @description
 * @date 2023/12/27 21:13:39
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ResponseStatus(value = HttpStatus.OK)
    @ExceptionHandler(value = ChaseBaseException.class)
    public BaseResponse<Void> handlerChaseBaseException(ChaseBaseException e) {
        return BaseResponse.ofFailure(e.getErrorCode(), e.getMessage());
    }

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = RuntimeException.class)
    public BaseResponse<Void> handlerChaseBaseException(RuntimeException e) {
        return BaseResponse.ofFailure(BaseResultCode.INTERNAL_SERVER_ERROR);
    }
}
