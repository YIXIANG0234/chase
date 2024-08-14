package edu.hhuc.yixiang.service.core.log;

import edu.hhuc.yixiang.service.core.log.function.IParseFunction;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author guwanghuai
 * @version 1.0
 * @project chase
 * @description
 * @date 2023/12/29 11:13:39
 */
@Component
public class ParseFunctionFactory {
    private final static Map<String, IParseFunction<?>> PARSE_FUNCTION = new ConcurrentHashMap<>();

    public ParseFunctionFactory(Map<String, IParseFunction<?>> parseFunctionBeans) {
        if (Objects.nonNull(parseFunctionBeans) && !parseFunctionBeans.isEmpty()) {
            PARSE_FUNCTION.putAll(parseFunctionBeans);
        }
    }

    public static IParseFunction<?> getFunction(String functionName) {
        return PARSE_FUNCTION.get(functionName);
    }

    /**
     * SpEL中所有自定义函数的执行入口
     * @param functionName IParseFunction.functionName()定义的方法名称
     * @param args 参数
     * @return 返回结果
     */
    public static Object execute(String functionName, Object... args) {
        IParseFunction<?> function = ParseFunctionFactory.getFunction(functionName);
        return function.apply(args);
    }
}
