package edu.hhuc.yixiang.service.app.controller;

import edu.hhuc.yixiang.common.base.BaseResponse;
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
    public BaseResponse<Void> lock() {
        redisService.distributedLock();
        return BaseResponse.ofSuccess();
    }

    @GetMapping("/tryLock")
    public BaseResponse<Void> tryLock() {
        redisService.tryLock();
        return BaseResponse.ofSuccess();
    }
}
