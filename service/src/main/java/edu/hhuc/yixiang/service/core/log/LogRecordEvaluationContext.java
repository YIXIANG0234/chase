package edu.hhuc.yixiang.service.core.log;

import edu.hhuc.yixiang.service.context.LogRecordContext;
import org.springframework.context.expression.MethodBasedEvaluationContext;
import org.springframework.core.ParameterNameDiscoverer;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Objects;

/**
 * @author guwanghuai
 * @version 1.0
 * @project chase
 * @description
 * @date 2023/12/28 21:17:45
 */
public class LogRecordEvaluationContext extends MethodBasedEvaluationContext {
    public static final String METHOD_RESULT = "_result";

    public LogRecordEvaluationContext(Object rootObject, Method method, Object[] arguments, ParameterNameDiscoverer parameterNameDiscoverer, Object methodResult) {
        super(rootObject, method, arguments, parameterNameDiscoverer);
        // 将方法的执行结果放入表达式上下文中
        if (Objects.nonNull(methodResult)) {
            setVariable(METHOD_RESULT, methodResult);
        }
        // 将LogRecordContext中当前方法栈的参数放入表达式上下文中
        Map<String, Object> variables = LogRecordContext.getVariables();
        if (Objects.nonNull(variables) && !variables.isEmpty()) {
            variables.forEach(this::setVariable);
        }
    }

    public static LogRecordEvaluationContext createEvaluationContext(Method method, Object[] arguments, ParameterNameDiscoverer parameterNameDiscoverer, Object methodResult) {
        return new LogRecordEvaluationContext(null, method, arguments, parameterNameDiscoverer, methodResult);
    }
}
