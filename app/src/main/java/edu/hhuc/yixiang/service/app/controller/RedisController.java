package edu.hhuc.yixiang.service.app.controller;

import edu.hhuc.yixiang.service.core.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author guwanghuai
 * @version 1.0
 * @project chase
 * @description
 * @date 2023/12/27 16:18:26
 */
@RestController
@RequestMapping("/redis")
public class RedisController {
    @Autowired
    private RedisService redisService;

    @GetMapping("/lock")
    public void lock() {
        redisService.distributedLock();
    }

    @GetMapping("/tryLock")
    public void tryLock() {
        redisService.tryLock();
    }
}
