package edu.hhuc.yixiang.service.aop;

import edu.hhuc.yixiang.common.annotation.Limiter;
import edu.hhuc.yixiang.common.base.BaseResultCode;
import edu.hhuc.yixiang.common.base.UserHolder;
import edu.hhuc.yixiang.common.constant.RedisConstants;
import edu.hhuc.yixiang.common.enums.LimiteDimensionEnum;
import edu.hhuc.yixiang.common.exception.RateLimitException;
import edu.hhuc.yixiang.common.utils.IPUtil;
import edu.hhuc.yixiang.service.component.limit.LimitExecutor;
import edu.hhuc.yixiang.service.component.limit.LimitExecutorFactory;
import edu.hhuc.yixiang.service.component.limit.RateLimitConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author guwanghuai
 * @version 1.0
 * @project chase
 * @description
 * @date 2024/1/8 17:29:21
 */
@Component
@Aspect
@Slf4j
public class LimiterAspect {
    @Autowired
    private LimitExecutorFactory limitExecutorFactory;

    @Pointcut(value = "@annotation(edu.hhuc.yixiang.common.annotation.Limiter)")
    public void pointCut() {
    }

    @Before(value = "pointCut()")
    public void before(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Limiter limiter = method.getAnnotation(Limiter.class);
        LimitExecutor executor = limitExecutorFactory.getExecutor(limiter.executor());

        String key = getLimitKey(limiter, method.getName());
        RateLimitConfiguration configuration = new RateLimitConfiguration(key, limiter.threshold(), limiter.window(), limiter.rate());
        boolean acquire = executor.acquire(configuration);
        if (!acquire) {
            // 已达限流阈值
            throw new RateLimitException(BaseResultCode.RATE_LIMIT_EXCEPTION);
        }
    }


    private String getLimitKey(Limiter limiter, String methodName) {
        LimiteDimensionEnum dimension = limiter.dimension();
        if (LimiteDimensionEnum.IP.equals(dimension)) {
            return String.format(RedisConstants.LIMITER_PREFIX, limiter.executor().getCode(), limiter.dimension().getCode(), methodName, IPUtil.getClientIp());
        }
        if (LimiteDimensionEnum.USER.equals(dimension)) {
            return String.format(RedisConstants.LIMITER_PREFIX, limiter.executor().getCode(), limiter.dimension().getCode(), methodName, UserHolder.getUserId());
        }
        return String.format(RedisConstants.LIMITER_PREFIX, limiter.executor().getCode(), limiter.dimension().getCode(), methodName, LimiteDimensionEnum.SERVICE.getCode());
    }
}
