package edu.hhuc.yixiang.service.aop;

import edu.hhuc.yixiang.common.annotation.DistributedLock;
import edu.hhuc.yixiang.common.constant.RedisConstants;
import edu.hhuc.yixiang.common.exception.DistributedLockException;
import edu.hhuc.yixiang.service.helper.RedisHelper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.UUID;

/**
 * @author guwanghuai
 * @version 1.0
 * @project chase
 * @description
 * @date 2023/12/26 21:39:50
 */
@Aspect
@Component
public class DistributedLockAspect {

    @Pointcut(value = "@annotation(edu.hhuc.yixiang.common.annotation.DistributedLock)")
    public void pointCut() {
    }

    @Around(value = "pointCut()")
    public Object around(ProceedingJoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        DistributedLock annotation = method.getAnnotation(DistributedLock.class);
        String key = RedisConstants.DISTRIBUTED_LOCK_PREFIX + annotation.key();
        String uuid = UUID.randomUUID().toString();
        try {
            // 非阻塞获取锁
            long start = System.currentTimeMillis();
            if (RedisHelper.lock(key, uuid, RedisConstants.DISTRIBUTED_LOCK_DEFAULT_TIMEOUT)) {
                System.out.println(Thread.currentThread().getName() + "，获取锁耗时:" + (System.currentTimeMillis() - start));
                return joinPoint.proceed();
            }
            // 阻塞式获取锁
            if (annotation.retry()) {
                if (RedisHelper.tryLock(key, uuid, RedisConstants.DISTRIBUTED_LOCK_DEFAULT_TIMEOUT)) {
                    System.out.println(Thread.currentThread().getName() + "，获取锁耗时:" + (System.currentTimeMillis() - start));
                    return joinPoint.proceed();
                }
            }
            throw new DistributedLockException("获取锁失败");
        } catch (Throwable e) {
            throw new DistributedLockException("获取锁失败", e);
        } finally {
            RedisHelper.releaseLock(key, uuid);
        }
    }
}
