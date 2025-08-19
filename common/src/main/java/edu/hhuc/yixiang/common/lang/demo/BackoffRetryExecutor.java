package edu.hhuc.yixiang.common.lang.demo;

/**
 * @version 1.0
 * @project chase
 * @description
 * @date 2025/8/12 13:55:29
 */

import java.io.IOException;
import java.time.Duration;
import java.util.Random;
import java.util.function.Supplier;

public class BackoffRetryExecutor {
    // 最大重试次数
    private final int maxAttempts;
    // 初始延迟
    private final Duration initialDelay;
    // 最大延迟时间
    private final Duration maxDelay;
    // 退避因子（通常>1）
    private final double backoffFactor;
    // 随机抖动因子（0-1）
    private final double jitterFactor;
    // 可重试的异常类型
    private final Class<? extends Exception>[] retryableExceptions;

    @SafeVarargs
    public BackoffRetryExecutor(int maxAttempts,
            Duration initialDelay,
            Duration maxDelay,
            double backoffFactor,
            double jitterFactor,
            Class<? extends Exception>... retryableExceptions) {
        this.maxAttempts = maxAttempts;
        this.initialDelay = initialDelay;
        this.maxDelay = maxDelay;
        this.backoffFactor = backoffFactor;
        this.jitterFactor = jitterFactor;
        this.retryableExceptions = retryableExceptions;
    }

    public <T> T execute(Supplier<T> task) throws Exception {
        int attempt = 0;
        long delayMillis = initialDelay.toMillis();
        Random random = new Random();

        while (true) {
            attempt++;
            try {
                return task.get();  // 执行实际任务
            } catch (Exception e) {
                // 检查是否为可重试异常
                if (!isRetryable(e)) {
                    throw e;
                }

                // 检查是否超过最大重试次数
                if (attempt >= maxAttempts) {
                    throw new RetryException("Operation failed after " + maxAttempts + " attempts", e);
                }

                // 计算退避时间（指数增长 + 随机抖动）
                long nextDelay = Math.min(maxDelay.toMillis(), (long) (delayMillis * backoffFactor));
                long jitter = (long) (nextDelay * jitterFactor * (random.nextDouble() - 0.5) * 2);
                long actualDelay = Math.max(0, nextDelay + jitter);

                // 打印日志（实际应用中可用Logger替换）
                System.out.printf("Attempt %d failed. Retrying in %dms (cause: %s)%n", attempt, actualDelay, e.getMessage());

                // 等待退避时间
                try {
                    Thread.sleep(actualDelay);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                    throw new RetryException("Retry interrupted", ie);
                }
                // 更新下一次的延迟时间
                delayMillis = nextDelay;
            }
        }
    }

    // 检查异常是否可重试
    private boolean isRetryable(Exception ex) {
        // 默认所有异常都可重试
        if (retryableExceptions.length == 0) {
            return true;
        }

        for (Class<? extends Exception> exType : retryableExceptions) {
            if (exType.isInstance(ex)) {
                return true;
            }
        }
        return false;
    }

    // 自定义重试异常
    public static class RetryException extends Exception {
        public RetryException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    // 使用示例
    public static void main(String[] args) {
        // 配置退避策略：最大5次尝试，初始延迟100ms，最大延迟5秒，指数因子2，抖动因子0.3
        BackoffRetryExecutor executor = new BackoffRetryExecutor(
                5,
                Duration.ofMillis(100),
                Duration.ofSeconds(5),
                2.0,
                0.3,
                IllegalStateException.class, IOException.class
        );

        try {
            String result = executor.execute(() -> {
                // 模拟可能失败的业务逻辑
                if (Math.random() > 0.3) {
                    throw new IllegalStateException("Service unavailable");
                }
                return "Success!";
            });
            System.out.println("Final result: " + result);
        } catch (Exception e) {
            System.err.println("Operation failed: " + e.getMessage());
        }
    }
}
