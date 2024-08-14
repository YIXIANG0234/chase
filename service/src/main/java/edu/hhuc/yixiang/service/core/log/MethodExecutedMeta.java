package edu.hhuc.yixiang.service.core.log;

import lombok.Getter;
import org.springframework.context.expression.AnnotatedElementKey;

import java.lang.reflect.Method;

/**
 * @author guwanghuai
 * @version 1.0
 * @project chase
 * @description
 * @date 2024/8/12 14:23:13
 */
@Getter
public class MethodExecutedMeta {
    private final Method method;
    private final Object[] args;
    private final Object target;
    private final Class<?> targetClass;
    private Object result;

    public MethodExecutedMeta(Method method, Object[] args, Object target, Class<?> targetClass) {
        this.method = method;
        this.args = args;
        this.target = target;
        this.targetClass = targetClass;
    }

    public AnnotatedElementKey getAnnotatedElementKey() {
        return new AnnotatedElementKey(method, targetClass);
    }

    public void attachResult(Object result) {
        this.result = result;
    }
}
