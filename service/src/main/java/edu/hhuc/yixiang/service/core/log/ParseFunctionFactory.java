package edu.hhuc.yixiang.service.core.log;

import org.springframework.stereotype.Component;

import java.util.Map;
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
    private final Map<String, IParseFunction> parseFunctionMap;
    public ParseFunctionFactory(Map<String, IParseFunction> parseFunctionBeans) {
        parseFunctionMap = new ConcurrentHashMap<>();
        parseFunctionBeans.forEach((key, value) -> parseFunctionMap.put(value.functionName(), value));
    }

    public IParseFunction getParseFunction(String functionName) {
        return parseFunctionMap.get(functionName);
    }
}
