package edu.hhuc.yixiang.service.app.controller;

import edu.hhuc.yixiang.common.base.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author guwanghuai
 * @version 1.0
 * @project chase
 * @description
 * @date 2024/8/26 21:19:21
 */
@Slf4j
@RestController
@RequestMapping("/redis")
public class RedisGuideController {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private final AtomicInteger threadIndex = new AtomicInteger(0);

    @GetMapping("/producer")
    public BaseResponse<String> producer() {
        String key = "chase:queue:list";
        String code = RandomStringUtils.randomAlphabetic(5);
        redisTemplate.opsForList().leftPush(key, code);
        return BaseResponse.ofSuccess(code);
    }

    @GetMapping("/consumer")
    public BaseResponse<Void> consumer() {
        String key = "chase:queue:list";
        new Thread(() -> {
            String code = "";
            while (!"exist".equalsIgnoreCase(code)) {
                code = (String) redisTemplate.opsForList().rightPop(key, Duration.ZERO);
                log.info("{} receive message code：{}", Thread.currentThread().getName(), code);
            }
        }, "消费者线程" + threadIndex.incrementAndGet()).start();
        log.info("consumer started");
        return BaseResponse.ofSuccess();
    }

    @GetMapping("/hyperLogLog")
    public BaseResponse<Long> hyperLogLog() {
        String setKey = "chase:set:hyperLogLog";
        String hllKey = "chase:hll:hyperLogLog";
        String code = RandomStringUtils.randomAlphabetic(4);
        redisTemplate.opsForSet().add(setKey, code);
        redisTemplate.opsForHyperLogLog().add(hllKey, code);
        return BaseResponse.ofSuccess(redisTemplate.opsForHyperLogLog().size(hllKey));
    }
}
