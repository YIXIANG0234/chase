package edu.hhuc.yixiang.service.app.controller;

import edu.hhuc.yixiang.common.base.BaseResponse;
import edu.hhuc.yixiang.common.dto.AnyInputDTO;
import edu.hhuc.yixiang.common.entity.User;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author guwanghuai
 * @version 1.0
 * @project chase
 * @description
 * @date 2024/8/15 15:45:15
 */
@RestController
@RequestMapping("/threadLocal")
@Slf4j
public class ThreadLocalController {
    private final ThreadLocal<Integer> threadLocal = new ThreadLocal<>();
    private final ThreadLocal<Integer> inheritableThreadLocal = new InheritableThreadLocal<>();
    private ThreadPoolExecutor executor;

    @GetMapping("/setThreadLocal")
    public BaseResponse<String> setThreadLocal() {
        Random random = new Random();
        Integer num = random.nextInt(100);
        threadLocal.set(num);
        return BaseResponse.ofSuccess(num + ":" + threadLocal.get());
    }

    @GetMapping("/setInheritableThreadLocal")
    public BaseResponse<String> setInheritableThreadLocal() {
        Random random = new Random();
        Integer num = random.nextInt(100);
        inheritableThreadLocal.set(num);
        return BaseResponse.ofSuccess(num + ":" + inheritableThreadLocal.get());
    }

    @GetMapping("/get")
    public BaseResponse<String> get() {
        Integer num1 = threadLocal.get();
        Integer num2 = inheritableThreadLocal.get();
        return BaseResponse.ofSuccess(num1 + ":" + num2);
    }

    @GetMapping("/executorGet")
    public BaseResponse<String> executorGet() {
        threadLocal.set(100);
        inheritableThreadLocal.set(200);
        executor.execute(() -> {
            log.info("{} 访问 ThreadLocal的值：{}-{}", Thread.currentThread().getName(), threadLocal.get(), inheritableThreadLocal.get());
        });
        return BaseResponse.ofSuccess(threadLocal.get() + ":" + inheritableThreadLocal.get());
    }

    /**
     * 测试本方法需要将tomcat并发线程数设置为1比较方便
     *
     * @param inputDTO 输入
     * @return 返回
     */
    @PostMapping("/leak")
    public BaseResponse<String> leak(@RequestBody AnyInputDTO inputDTO) {
        if ("gc".equals(inputDTO.getValue())) {
            return BaseResponse.ofSuccess(gc());
        }
        ThreadLocal<String> threadLocal = new ThreadLocal<>();
        threadLocal.set(inputDTO.getValue());
        log.info("stage value：" + threadLocal.get());
        return BaseResponse.ofSuccess(threadLocal.get());
    }

    @GetMapping("/reference")
    public BaseResponse<Void> reference() {
        User user = new User();
        user.setNickName("reference");
        SoftReference<byte[]> softReference = new SoftReference<>(new byte[1024 * 1024 * 10]);
        WeakReference<User> weakReference = new WeakReference<>(new User());

        ReferenceQueue<User> referenceQueue = new ReferenceQueue<>();
        PhantomReference<User> phantomReference = new PhantomReference<>(new User(), referenceQueue);
        System.gc();
        log.info("强引用：{}", Objects.isNull(user) ? "被回收了" : user);
        log.info("软引用：{}", Objects.isNull(softReference.get()) ? "被回收了" : softReference.get().length);
        log.info("弱引用：{}", Objects.isNull(weakReference.get()) ? "被回收了" : weakReference.get());
        log.info("虚引用：{}", phantomReference.get());
        log.info("弱引用：{}", Objects.nonNull(referenceQueue.poll()) ? "被回收了" : "还没回收");
        return BaseResponse.ofSuccess();
    }

    public static void main(String[] args) {
        test3();
    }

    private static void test1() {
        ThreadLocal<Integer> threadLocal = new ThreadLocal<>();
        ThreadLocal<Integer> inheritableThreadLocal = new InheritableThreadLocal<>();
        threadLocal.set(50);
        inheritableThreadLocal.set(100);
        System.out.println("主线程:" + threadLocal.get() + ":" + inheritableThreadLocal.get());

        Thread thread = new Thread(() -> {
            // 由于子线程不会继承threadLocal，所有threadLocal.get()是null
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("子线程:" + threadLocal.get() + ":" + inheritableThreadLocal.get());
        });
        thread.start();
        inheritableThreadLocal.set(500);
        System.out.println("主线程:" + threadLocal.get() + ":" + inheritableThreadLocal.get());
    }

    private static void test2() {
        ThreadLocal<User> threadLocal = new ThreadLocal<>();
        ThreadLocal<User> inheritableThreadLocal = new InheritableThreadLocal<>();
        User user = new User();
        user.setNickName("test2");
        threadLocal.set(user);
        inheritableThreadLocal.set(user);

        System.out.println("主线程:" + threadLocal.get().getNickName() + ":" + inheritableThreadLocal.get().getNickName());
        Thread thread = new Thread(() -> {
            // 由于子线程不会继承threadLocal，所有threadLocal.get()是null
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("子线程:" + (Objects.nonNull(threadLocal.get()) ? threadLocal.get().getNickName() : "") + ":" + inheritableThreadLocal.get().getNickName());
        });
        thread.start();
        inheritableThreadLocal.get().setNickName("changed");
        System.out.println("主线程:" + threadLocal.get().getNickName() + ":" + inheritableThreadLocal.get().getNickName());
    }

    private String gc() {
        System.gc();
        ThreadLocal<String> threadLocal = new ThreadLocal<>();
        threadLocal.set("end");
        return threadLocal.get();
    }

    private static void test3() {
        stageIn();
        System.gc();
        ThreadLocal<String> end = new ThreadLocal<>();
        end.set("end");
        System.out.println(end.get());
    }

    private static void stageIn() {
        ThreadLocal<String> var1 = new ThreadLocal<>();
        ThreadLocal<String> var2 = new ThreadLocal<>();
        ThreadLocal<String> var3 = new ThreadLocal<>();
        var1.set("a");
        var2.set("b");
        var3.set("c");

    }

    @PostConstruct
    public void init() {
        executor = new ThreadPoolExecutor(2, 5, 10, TimeUnit.SECONDS, new ArrayBlockingQueue<>(100));
        // 初始化所有核心线程
        System.out.println("当前线程：" + Thread.currentThread().getName());
        executor.prestartAllCoreThreads();
    }
}
