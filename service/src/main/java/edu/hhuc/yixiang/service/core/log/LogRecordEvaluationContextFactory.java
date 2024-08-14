package edu.hhuc.yixiang.service.core.log;

import lombok.extern.slf4j.Slf4j;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.lang.reflect.Method;

/**
 * @author guwanghuai
 * @version 1.0
 * @project chase
 * @description
 * @date 2024/8/12 14:10:44
 */
@Slf4j
public class LogRecordEvaluationContextFactory {
    private final StandardEvaluationContext originalContext;
    private final String EXECUTE = "execute";

    public LogRecordEvaluationContextFactory(StandardEvaluationContext originalContext) {
        this.originalContext = originalContext;
    }

    public LogRecordEvaluationContext forOperation(Method targetMethod, Object[] args, Object result) {
        LogRecordEvaluationContext evaluationContext = new LogRecordEvaluationContext(null, targetMethod, args, result);
        // 注册自定义函数
        try {
            Method method = ParseFunctionFactory.class.getDeclaredMethod(EXECUTE, String.class, Object[].class);
            evaluationContext.registerFunction(EXECUTE, method);
        } catch (NoSuchMethodException e) {
            log.error("自定义函数注册失败", e);
            // do nothing
        }
        this.originalContext.applyDelegatesTo(evaluationContext);
        return evaluationContext;
    }
}
