package edu.hhuc.yixiang.service.app.controller;

import edu.hhuc.yixiang.common.annotation.Limiter;
import edu.hhuc.yixiang.common.base.BaseResponse;
import edu.hhuc.yixiang.common.enums.LimitExecutorEnum;
import edu.hhuc.yixiang.common.utils.DateUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

/**
 * @author guwanghuai
 * @version 1.0
 * @project chase
 * @description
 * @date 2024/1/8 20:54:37
 */
@RestController
@RequestMapping("/limit")
public class RateLimitController {
    @Limiter(executor = LimitExecutorEnum.GUAVA)
    @GetMapping("/guava")
    public BaseResponse<Integer> guava() throws Exception {
        Random random = new Random();
        int sleep = random.nextInt(500);
        Thread.sleep(sleep);
        return BaseResponse.ofSuccess(sleep);
    }

    /**
     * @return 测试固定窗口限流算法
     */
    @Limiter(window = 5000000)
    @GetMapping("/window")
    public BaseResponse<Integer> fixedWindow() throws Exception {
        Random random = new Random();
        int sleep = random.nextInt(500);
        Thread.sleep(sleep);
        return BaseResponse.ofSuccess(sleep);
    }

    @GetMapping("/slide")
    @Limiter(executor = LimitExecutorEnum.SLIDE)
    public BaseResponse<Integer> slideWindow() throws Exception {
        Random random = new Random();
        int sleep = random.nextInt(500);
        Thread.sleep(sleep);
        return BaseResponse.ofSuccess(sleep);
    }

    @GetMapping("/token")
    @Limiter(executor = LimitExecutorEnum.TOKEN, rate = 1, threshold = 500)
    public BaseResponse<Integer> token() throws Exception {
        Random random = new Random();
        int sleep = random.nextInt(500);
        Thread.sleep(sleep);
        return BaseResponse.ofSuccess(sleep);
    }

    @GetMapping("/leaky")
    @Limiter(executor = LimitExecutorEnum.LEAKY, rate = 1, threshold = 5)
    public BaseResponse<Integer> leaky() {
        Random random = new Random();
        int sleep = random.nextInt(500);
        System.out.println("正在处理请求：" + DateUtil.formatNowPrecise());
        return BaseResponse.ofSuccess(sleep);
    }
}
