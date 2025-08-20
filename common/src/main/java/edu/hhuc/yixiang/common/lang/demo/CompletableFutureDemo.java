package edu.hhuc.yixiang.common.lang.demo;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;

/**
 * @version 1.0
 * @project chase
 * @description
 * @date 2025/8/19 20:19:32
 */
public class CompletableFutureDemo {
    private static final String START = "Thread 【%s】is executing task";
    private static final String END = "Thread 【%s】completed the task";
    private static final Executor EXECUTOR = new ThreadPoolExecutor(5
            , 5
            , 0L
            , TimeUnit.MILLISECONDS
            , new LinkedBlockingQueue<>()
            , new MyThreadFactory("FuturePool"));

    public static void main(String[] args) throws Exception {
        test9();
    }

    public static void test1() throws Exception {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(new CallableTask().get(), EXECUTOR);
        String result = future.get();
        System.out.println("子任务执行完毕：" + result);
    }

    public static void test2() throws Exception {
        CompletableFuture<Void> future = CompletableFuture.runAsync(new RunnableTask(), EXECUTOR);
        Void result = future.get();
        System.out.println("子任务执行完毕：" + result);
    }

    public static void test3() throws Exception {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(new CallableTask().get(), EXECUTOR).thenApply(t -> {
            print("thenApply: " + t);
            return "aaa";
        }).thenApplyAsync(t -> {
            print("thenApplyAsync: " + t);
            return "bbb";
        }, EXECUTOR);
        System.out.println("任务执行结果：" + future.get());
    }

    public static void test4() throws Exception {
        CompletableFuture<Void> future = CompletableFuture.supplyAsync(new CallableTask().get(), EXECUTOR).thenAccept(t -> {
            print("thenAccept: " + t);
        }).thenAcceptAsync(t -> {
            print("thenAcceptAsync:" + t);
        }, EXECUTOR);
        System.out.println("任务执行结果：" + future.get());
    }

    public static void test5() throws Exception {
        CompletableFuture<Void> future = CompletableFuture.supplyAsync(new CallableTask().get(), EXECUTOR)
                .thenRun(() -> print("thenRun"))
                .thenRunAsync(() -> print("thenRunAsync"), EXECUTOR);

        System.out.println("任务执行结果：" + future.get());
    }

    public static void test6() throws Exception {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(new CallableTask().get(), EXECUTOR)
                .thenCompose(s -> CompletableFuture.supplyAsync(new CallableTask().get(), EXECUTOR));
        System.out.println("任务执行结果：" + future.get());
    }

    public static void test7() throws Exception {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
                    Random random = new Random();
                    int num = random.nextInt(100);
                    if (num < 30) {
                        throw new RuntimeException("有30%的概率发生异常");
                    }
                    return num;
                }, EXECUTOR)
                .handle((result, e) -> {
                    if (e == null) {
                        System.out.println("任务执行成功：" + result);
                        return result * 100;
                    }
                    System.out.println("异常信息：" + e.getMessage());
                    return -1;
                });
        System.out.println("任务执行结果：" + future.get());
    }

    public static void test8() throws Exception {
        Supplier<Integer> task = () -> {
            Random random = new Random();
            int num = random.nextInt(100);
            if (num < 30) {
                throw new RuntimeException("有30%的概率发生异常");
            }
            return num;
        };
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(task, EXECUTOR);
        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(task, EXECUTOR);
        CompletableFuture<Void> future = CompletableFuture.allOf(future1, future2);

        System.out.println("任务执行结果：" + future.get());
        System.out.println("future1执行结果：" + future1.get());
        System.out.println("future2执行结果：" + future2.get());
    }

    public static void test9() throws Exception {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(new CallableTask().get(), EXECUTOR);
        while (!future.isDone()) {
            Thread.sleep(5);
        }
        future.thenApply(t -> {
            print("thenApply: " + t);
            return "hello world";
        });
        System.out.println("任务执行结果：" + future.get());
    }


    private static void print(String message) {
        System.out.println(String.format("thread 【%s】 is executing, message: 【%s】", Thread.currentThread().getName(), message));
    }

    private static class RunnableTask implements Runnable {
        @Override
        public void run() {
            try {
                System.out.println(String.format(START, Thread.currentThread().getName()));
                Random random = new Random();
                int sleep = random.nextInt(500);
                Thread.sleep(sleep);
                System.out.println(String.format(END, Thread.currentThread().getName()));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static class CallableTask implements Callable<String> {
        @Override
        public String call() {
            try {
                System.out.println(String.format(START, Thread.currentThread().getName()));
                Random random = new Random();
                int sleep = random.nextInt(500);
                Thread.sleep(sleep);
                System.out.println(String.format(END, Thread.currentThread().getName()));
                return String.format("【result: %s】", Thread.currentThread().getName());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        public Supplier<String> get() {
            return this::call;
        }
    }

    private static class MyThreadFactory implements ThreadFactory {
        private ThreadGroup threadGroup;
        private AtomicInteger threadCount;

        public MyThreadFactory(String threadGroupName) {
            this.threadGroup = new ThreadGroup(threadGroupName);
            threadCount = new AtomicInteger();
        }

        @Override
        public Thread newThread(Runnable r) {
            return new Thread(threadGroup, r, threadGroup.getName() + "-thread-" + threadCount.incrementAndGet(), 0);
        }
    }
}
