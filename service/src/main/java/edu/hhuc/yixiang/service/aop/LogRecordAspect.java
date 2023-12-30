package edu.hhuc.yixiang.service.aop;

import com.google.common.collect.Lists;
import edu.hhuc.yixiang.common.annotation.LogRecord;
import edu.hhuc.yixiang.service.context.LogRecordContext;
import edu.hhuc.yixiang.service.core.log.LogRecordParser;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author guwanghuai
 * @version 1.0
 * @project chase
 * @description
 * @date 2023/12/28 16:50:18
 */
@Aspect
@Slf4j
@Component
public class LogRecordAspect {
    @Autowired
    private LogRecordParser parser;

    @Pointcut(value = "@annotation(edu.hhuc.yixiang.common.annotation.LogRecord)")
    public void pointCut() {
    }

    @Around(value = "pointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        Object[] args = joinPoint.getArgs();
        Class<?> targetClass = AopProxyUtils.ultimateTargetClass(joinPoint.getThis());
        LogRecord logRecord = method.getAnnotation(LogRecord.class);
        // 重要！每次方法调用需要入栈
        LogRecordContext.pushMethodContext();

        // 支持EL表达式的字段
        List<String> expressions = getSupportExpress(logRecord);
        Map<String, String> functionParseResultMapping = new HashMap<>();
        try {
            // 解析需要在目标方法执行前执行的函数
            functionParseResultMapping = parser.parseExpressionBeforeExecuteFunction(expressions, targetClass, method, args);
        } catch (Exception e) {
            log.error("操作日志记录错误", e);
        }
        LogRecordContext.putVariable("startTime", new Date());
        Object result = joinPoint.proceed();
        LogRecordContext.putVariable("endTime", new Date());
        try {
            Map<String, String> expressionParseResult = parser.doParseCompletedExpression(expressions, targetClass, method, args, result, functionParseResultMapping);
            parser.doRecord(expressionParseResult, logRecord);
        } catch (Exception e) {
            log.error("操作日志记录错误", e);
        } finally {
            // 方法调用结束需要出栈
            LogRecordContext.popMethodContext();
        }
        return result;
    }

    private List<String> getSupportExpress(LogRecord logRecord) {
        return Lists.newArrayList(logRecord.content(), logRecord.operatorUser(), logRecord.businessId());
    }
}
