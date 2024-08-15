package edu.hhuc.yixiang.service.aop;

import com.google.common.collect.Lists;
import edu.hhuc.yixiang.common.annotation.LogRecord;
import edu.hhuc.yixiang.service.context.LogRecordContext;
import edu.hhuc.yixiang.service.core.log.LogAccessor;
import edu.hhuc.yixiang.service.core.log.LogRecordExecutor;
import edu.hhuc.yixiang.service.core.log.MethodExecutedMeta;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.StopWatch;
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
        StopWatch stopWatch = StopWatch.createStarted();
        try {
            executor.pushExpressions(getSupportExpress(logRecord), executedMeta);
            executor.parseBefore(executedMeta);
        } catch (Exception e) {
            log.error("操作日志记录错误", e);
            prevParseSuccess = false;
        }
        stopWatch.suspend();
        Object result;
        try {
            LogRecordContext.putVariable("startTime", new Date());
            result = joinPoint.proceed();
            LogRecordContext.putVariable("endTime", new Date());
        } catch (Exception e) {
            // 目标方法异常，将数据出栈，否则栈帧会一直累积，导致无法进行垃圾回收
            LogRecordContext.popMethodContext();
            executor.clearExpressions();
            throw e;
        }
        try {
            // 如果方法执行前的表达式执行失败了，就不记录日志了，避免记录的日志不完整
            if (!prevParseSuccess) {
                return result;
            }
            stopWatch.resume();
            executedMeta.attachResult(result);
            executor.parseAfter(executedMeta);
            Map<String, String> expressionValue = executor.getValue();
            stopWatch.stop();
            log.info("总计耗时：{}", stopWatch.getTime());
            LogRecordContext.putVariable("logDuration", (int) stopWatch.getTime());
            LogRecordContext.putVariable("operatorThread", Thread.currentThread().getName());
            accessor.persist(expressionValue, logRecord);
        } catch (Exception e) {
            log.error("操作日志记录错误", e);
        } finally {
            LogRecordContext.popMethodContext();
            executor.clearExpressions();
        }
        return result;
    }
}
