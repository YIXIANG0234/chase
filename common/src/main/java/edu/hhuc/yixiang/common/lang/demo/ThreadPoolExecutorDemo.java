package edu.hhuc.yixiang.common.lang.demo;

import edu.hhuc.yixiang.common.utils.DateUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * @version 1.0
 * @project chase
 * @description
 * @date 2025/8/11 14:51:11
 */
public class ThreadPoolExecutorDemo {
    public static void main(String[] args) throws Exception {
        //        test1();
        //        test2();
        //        test3();
        //        test4();
        //        test5();
        //        test6();
        //        test7();
        test8();
    }

    public static void test1() throws Exception {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(3, 5, 60, TimeUnit.MINUTES, new ArrayBlockingQueue<>(5));
        executor.execute(new SimpleTask(2, "任务1"));
        executor.execute(new SimpleTask(2, "任务2"));
        executor.execute(new SimpleTask(2, "任务3"));

        executor.execute(new SimpleTask(600, "任务4"));
        executor.execute(new SimpleTask(600, "任务5"));
        executor.execute(new SimpleTask(600, "任务6"));
        executor.execute(new SimpleTask(600, "任务7"));
        executor.execute(new SimpleTask(600, "任务8"));

        executor.execute(new SimpleTask(600, "任务9"));
        executor.execute(new SimpleTask(600, "任务10"));

        Thread.sleep(5000);
        System.out.println(executor);
        executor.execute(new SimpleTask(600, "任务11"));
        System.out.println(executor);
    }

    public static void test2() throws Exception {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(3, 5, 60, TimeUnit.MINUTES, new ArrayBlockingQueue<>(5));
        executor.execute(new SimpleTask(10, "任务1"));
        executor.execute(new SimpleTask(10, "任务2"));

        executor.shutdownNow();
        executor.execute(new SimpleTask(600, "任务3"));
    }

    public static void test3() throws Exception {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(3, 5, 60, TimeUnit.MINUTES, new ArrayBlockingQueue<>(5));
        executor.execute(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            int num = 10 / 0;
        });
        System.out.println(executor.getPoolSize() + ":" + executor.getActiveCount());
        Thread.sleep(4000);
        System.out.println(executor.getPoolSize() + ":" + executor.getActiveCount());
        System.out.println("task execute finished");

    }

    public static void test4() throws Exception {
        FutureTask<String> futureTask = new FutureTask<>(() -> {
            Random random = new Random();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 10; i++) {
                int randomIndex = random.nextInt(62); // 0 ~ 61
                char c;
                if (randomIndex < 10) {
                    // 数字：48 ~ 57
                    c = (char) (48 + randomIndex);
                } else if (randomIndex < 36) {
                    // 大写字母：65 ~ 90
                    c = (char) (65 + randomIndex - 10);
                } else {
                    // 小写字母：97 ~ 122
                    c = (char) (97 + randomIndex - 36);
                }
                sb.append(c);
            }
            return sb.toString();
        });
        Thread thread = new Thread(futureTask);
        thread.start();
        System.out.println("等待生成随机字符串：" + futureTask.get());
    }

    public static void test5() throws Exception {
        Thread t1 = new Thread(LockSupport::park, "线程1");
        Thread t2 = new Thread(() -> {
            try {
                Thread.sleep(600 * 1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "线程2");

        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }

    public static void test6() throws Exception {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 5, 60, TimeUnit.MINUTES, new ArrayBlockingQueue<>(5));
        executor.execute(new SimpleTask(600, "任务1"));
        executor.execute(new SimpleTask(600, "任务2"));
        executor.execute(new SimpleTask(600, "任务3"));
        System.out.println("当前线程池配置: " + executor);

        Thread t = new Thread(() -> {
            System.out.println("开始更新线程池配置: " + executor);
            executor.setCorePoolSize(5);
            executor.setMaximumPoolSize(10);
            executor.setKeepAliveTime(5, TimeUnit.MINUTES);
            System.out.println("更新线程池配置完成:" + executor);
        });
        t.start();

        Thread.sleep(500);

        executor.execute(new SimpleTask(600, "任务4"));
        executor.execute(new SimpleTask(600, "任务5"));
        System.out.println("当前线程池配置: " + executor);
    }

    public static void test7() throws Exception {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 1, 60, TimeUnit.MINUTES, new ArrayBlockingQueue<>(1));
        Future<String> future1 = executor.submit(new SimpleCall(5, "任务1"));
        Future<String> future2 = executor.submit(new SimpleCall(10, "任务2"));
        Future<String> future3 = executor.submit(new SimpleCall(15, "任务3"));
        System.out.println(future1.get());
        System.out.println(future2.get());
        System.out.println(future3.get());
        System.out.println("当前线程池配置: " + executor);
    }

    public static void test8() throws Exception {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 2, 60, TimeUnit.MINUTES, new ArrayBlockingQueue<>(1));
        Future<String> future1 = executor.submit(() -> {
            System.out.println(Thread.currentThread().getName() + ":开始执行任务");
            Future<String> future2 = executor.submit(() -> {
                System.out.println(Thread.currentThread().getName() + ":正在执行子任务1");
                return "子任务1";
            });
            Future<String> future3 = executor.submit(() -> {
                System.out.println(Thread.currentThread().getName() + ":正在执行子任务2");
                return "子任务2";
            });
            List<String> list = new ArrayList<>();
            list.add(future2.get());
            list.add(future3.get());
            System.out.println("执行完毕的子任务：" + list);
            return "主任务执行完毕";
        });

        System.out.println(future1.get());
        System.out.println("当前线程池配置: " + executor);
    }

    private static class SimpleTask implements Runnable, Callable<String> {
        private static final String LOG_START_TEMPLATE = "【%s】is running task【%s】at: %s";
        private static final String LOG_END_TEMPLATE = "【%s】is completed task【%s】at: %s";
        private int sleepSeconds;
        private String taskName;

        public SimpleTask(int sleepSeconds, String taskName) {
            this.sleepSeconds = sleepSeconds;
            this.taskName = taskName;
        }

        @Override
        public void run() {
            System.out.println(String.format(LOG_START_TEMPLATE, Thread.currentThread().getName(), this.taskName, DateUtil.formatNow()));
            try {
                Thread.sleep(sleepSeconds * 1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(String.format(LOG_END_TEMPLATE, Thread.currentThread().getName(), this.taskName, DateUtil.formatNow()));
        }

        @Override
        public String call() throws Exception {
            System.out.println(String.format(LOG_START_TEMPLATE, Thread.currentThread().getName(), this.taskName, DateUtil.formatNow()));
            try {
                Thread.sleep(sleepSeconds * 1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(String.format(LOG_END_TEMPLATE, Thread.currentThread().getName(), this.taskName, DateUtil.formatNow()));
            return Thread.currentThread().getName() + " execute finished";
        }
    }

    private static class SimpleCall implements Callable<String> {
        private static final String LOG_START_TEMPLATE = "【%s】is running task【%s】at: %s";
        private static final String LOG_END_TEMPLATE = "【%s】is completed task【%s】at: %s";
        private int sleepSeconds;
        private String taskName;

        public SimpleCall(int sleepSeconds, String taskName) {
            this.sleepSeconds = sleepSeconds;
            this.taskName = taskName;
        }

        @Override
        public String call() throws Exception {
            System.out.println(String.format(LOG_START_TEMPLATE, Thread.currentThread().getName(), this.taskName, DateUtil.formatNow()));
            try {
                Thread.sleep(sleepSeconds * 1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(String.format(LOG_END_TEMPLATE, Thread.currentThread().getName(), this.taskName, DateUtil.formatNow()));
            return Thread.currentThread().getName() + " execute finished";
        }
    }


}

