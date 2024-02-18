package edu.hhuc.yixiang.service.component.limit;

import com.google.common.util.concurrent.RateLimiter;
import edu.hhuc.yixiang.common.enums.LimitExecutorEnum;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author guwanghuai
 * @version 1.0
 * @project chase
 * @description
 * @date 2024/1/8 22:19:35
 */
@Component
public class GuavaLimitExecutor implements LimitExecutor {
    private Map<String, RateLimiter> rateLimiterMap = new ConcurrentHashMap<>();
    @Override
    public LimitExecutorEnum getExecutorType() {
        return LimitExecutorEnum.GUAVA;
    }

    @Override
    public boolean acquire(RateLimitConfiguration configuration) {
        String key = configuration.getKey();
        if (!rateLimiterMap.containsKey(key)) {
            synchronized (key) {
                if (!rateLimiterMap.containsKey(key)) {
                    rateLimiterMap.put(key, RateLimiter.create(configuration.getThreshold()));
                }
            }
        }
        RateLimiter rateLimiter = rateLimiterMap.get(key);
        return rateLimiter.tryAcquire(1, 0, TimeUnit.MICROSECONDS);
    }
}
