package edu.hhuc.yixiang.service.aop;

import com.google.common.collect.Lists;
import edu.hhuc.yixiang.common.annotation.LogRecord;
import edu.hhuc.yixiang.service.context.LogRecordContext;
import edu.hhuc.yixiang.service.core.log.LogAccessor;
import edu.hhuc.yixiang.service.core.log.LogRecordExecutor;
import edu.hhuc.yixiang.service.core.log.MethodExecutedMeta;
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
    private LogRecordExecutor executor;

    @Autowired
    private LogAccessor accessor;

    @Pointcut(value = "@annotation(edu.hhuc.yixiang.common.annotation.LogRecord)")
    public void pointCut() {
    }

    private List<String> getSupportExpress(LogRecord logRecord) {
        return Lists.newArrayList(logRecord.content(), logRecord.operatorUser(), logRecord.businessId());
    }

    @Around(value = "pointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Object[] args = joinPoint.getArgs();
        Object target = joinPoint.getTarget();
        Class<?> targetClass = AopProxyUtils.ultimateTargetClass(joinPoint.getThis());
        MethodExecutedMeta executedMeta = new MethodExecutedMeta(method, args, target, targetClass);

        LogRecordContext.pushMethodContext();
        LogRecord logRecord = method.getAnnotation(LogRecord.class);
        boolean prevParseSuccess = true;
        try {
            executor.pushExpressions(getSupportExpress(logRecord), executedMeta);
            executor.parseBefore(executedMeta);
        } catch (Exception e) {
            log.error("操作日志记录错误", e);
            prevParseSuccess = false;
        }
        Object result;
        try {
            LogRecordContext.putVariable("startTime", new Date());
            result = joinPoint.proceed();
            LogRecordContext.putVariable("endTime", new Date());
        } finally {
            // 特意处理joinPoint.proceed出异常的情况，要把相关数据出栈，否则出错的情况下，会在栈中逐渐累积数据，导致无法进行垃圾回收
            // LogRecordContext.popMethodContext();
            // executor.clearExpressions();
        }
        try {
            executedMeta.attachResult(result);
            executor.parseAfter(executedMeta);
            Map<String, String> expressionValue = executor.getValue();
            System.out.println(expressionValue);
            accessor.persist(expressionValue, logRecord);
        } catch (Exception e) {
            log.error("操作日志记录错误", e);
        } finally {
            // 方法调用结束需要出栈
            LogRecordContext.popMethodContext();
            executor.clearExpressions();
        }
        return result;
    }
}
