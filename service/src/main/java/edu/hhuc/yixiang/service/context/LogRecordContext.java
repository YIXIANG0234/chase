package edu.hhuc.yixiang.service.context;

import java.util.*;

/**
 * 操作日志上下文
 * @author guwanghuai
 * @version 1.0
 * @project chase
 * @description
 * @date 2023/12/28 16:59:16
 */
public class LogRecordContext {
    /**
     * 使用Stack栈，模拟方法栈的调用，InheritableThreadLocal对象用来进行线程间数据隔离
     * stack中的一个map代表一次方法调用，方法调用是将一个新的map入栈，方法执行结束时，栈顶map出栈
     * 每次操作都只操作栈顶的map，表示当前方法
     */
    private static final InheritableThreadLocal<Stack<Map<String, Object>>> CONTEXT = new InheritableThreadLocal<>();

    public static Object getVariable(String key) {
        if (Objects.isNull(getContext().get())) {
            return null;
        }
        Map<String, Object> top = getContext().get().peek();
        if (Objects.nonNull(top)) {
            return top.get(key);
        }
        return null;
    }

    public static void putVariable(String key, Object value) {
        Stack<Map<String, Object>> stack = getContext().get();
        if (Objects.isNull(stack)) {
            stack = new Stack<>();
            getContext().set(stack);
        }
        if (stack.isEmpty()) {
            stack.push(new HashMap<>());
        }
        stack.peek().put(key, value);
    }

    public static Map<String, Object> getVariables() {
        return getContext().get().peek();
    }

    /**
     * 每次方法调用时调用
     */
    public static void pushMethodContext() {
        if (Objects.isNull(getContext().get())) {
            CONTEXT.set(new Stack<>());
        }
        getContext().get().push(new HashMap<>());
    }

    public static void popMethodContext() {
        Stack<Map<String, Object>> stack = getContext().get();
        if (stack != null) {
            if (!stack.isEmpty()){
                stack.pop();
            }
        }
    }

    private static InheritableThreadLocal<Stack<Map<String, Object>>> getContext() {
        return CONTEXT;
    }
}
