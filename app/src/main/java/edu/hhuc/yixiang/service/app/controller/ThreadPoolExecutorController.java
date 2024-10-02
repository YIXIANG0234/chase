package edu.hhuc.yixiang.service.app.controller;

import edu.hhuc.yixiang.common.base.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author guwanghuai
 * @version 1.0
 * @project chase
 * @description
 * @date 2024/9/23 22:21:21
 */
@Slf4j
@RestController
@RequestMapping("/threadPool")
public class ThreadPoolExecutorController {
    ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 2, 1, TimeUnit.HOURS, new ArrayBlockingQueue<>(5), new ThreadPoolExecutor.AbortPolicy());

    @GetMapping("/submit")
    public BaseResponse<Void> submit() {
        executor.execute(() -> {
            log.info("线程{}：正在执行任务", Thread.currentThread());
            try {
                TimeUnit.HOURS.sleep(1L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            log.info("线程{}：执行任务完毕", Thread.currentThread());
        });
        return BaseResponse.ofSuccess();
    }

    @GetMapping("/state")
    public BaseResponse<Void> state() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            System.out.printf("4.%s state：%s\n", Thread.currentThread().getName(), Thread.currentThread().getState());
        }, "测试线程");
        System.out.printf("1.%s state：%s\n", t1.getName(), t1.getState());
        t1.start();
        System.out.printf("2.%s state：%s\n", t1.getName(), t1.getState());
        t1.join();
        System.out.printf("3.%s state：%s\n", t1.getName(), t1.getState());
        return BaseResponse.ofSuccess();
    }
}
