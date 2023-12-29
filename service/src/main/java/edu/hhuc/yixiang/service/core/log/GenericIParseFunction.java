package edu.hhuc.yixiang.service.core.log;

import org.springframework.stereotype.Component;

import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author guwanghuai
 * @version 1.0
 * @project chase
 * @description
 * @date 2023/12/29 11:18:56
 */
@Component
public class GenericIParseFunction implements IParseFunction {
    @Override
    public String functionName() {
        return "GenericIParseFunction";
    }

    @Override
    public String apply(Object... args) {
        String result = Stream.of(args).map(String::valueOf).collect(Collectors.joining("，"));
        return "【" + result + "】" + " length is :" + result.length();
    }
}
