package edu.hhuc.yixiang.service.helper;

import edu.hhuc.yixiang.common.constant.RedisConstants;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author guwanghuai
 * @version 1.0
 * @project chase
 * @description
 * @date 2023/12/26 16:58:46
 */
@Component
public class RedisHelper {
    private static RedisTemplate<String, Object> redisTemplate;

    public RedisHelper(RedisTemplate<String, Object> redisTemplate) {
        RedisHelper.redisTemplate = redisTemplate;
    }


    public static void set(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public static Boolean setNxExpire(String key, String value, Long milliSeconds) {
        return redisTemplate.opsForValue().setIfAbsent(key, value, milliSeconds, TimeUnit.MILLISECONDS);
    }

    /**
     * 设置key，同时设置过期时间，单位是秒
     *
     * @param key
     * @param value
     * @param milliSeconds
     */
    public static void set(String key, String value, long milliSeconds) {
        redisTemplate.opsForValue().set(key, value, milliSeconds, TimeUnit.MILLISECONDS);
    }

    public static String get(String key) {
        return (String) redisTemplate.opsForValue().get(key);
    }

    public static Boolean delete(String key) {
        return redisTemplate.delete(key);
    }

    public static Long ttl(String key) {
        return redisTemplate.getExpire(key);
    }

    public static boolean lock(String key, String value, long milliSeconds) {
        return Boolean.TRUE.equals(redisTemplate.opsForValue().setIfAbsent(key, value, milliSeconds, TimeUnit.MILLISECONDS));
    }

    public static boolean tryLock(String key, String value, long milliSeconds) {
        if (lock(key, value, milliSeconds)) {
            return true;
        }
        long timeoutAt = System.currentTimeMillis() + RedisConstants.DISTRIBUTED_LOCK_TRY_TIMEOUT;
        while (System.currentTimeMillis() < timeoutAt) {
            if (lock(key, value, milliSeconds)) {
                return true;
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                // do nothing
            }
        }
        return false;
    }

    public static boolean releaseLock(String key, String value) {
        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        RedisScript<Long> defaultRedisScript = new DefaultRedisScript<>(script, Long.class);
        Long result = redisTemplate.execute(defaultRedisScript, Collections.singletonList(key), value);
        return Objects.equals(1L, result);
    }
}
