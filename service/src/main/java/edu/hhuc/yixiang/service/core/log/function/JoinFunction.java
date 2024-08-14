package edu.hhuc.yixiang.service.core.log.function;

import org.springframework.stereotype.Component;

/**
 * @author guwanghuai
 * @version 1.0
 * @project chase
 * @description
 * @date 2023/12/29 11:18:56
 */
@Component
public class JoinFunction implements IParseFunction<String> {
    @Override
    public String functionName() {
        return "joinFunction";
    }

    @Override
    public String apply(Object... args) {
        StringBuilder sb = new StringBuilder("args join：");
        sb.append("【");
        for (int i = 0; i < args.length; i++) {
            sb.append(args[i].toString());
            if (i != args.length - 1) {
                sb.append("：");
            }
        }
        sb.append("】");
        return sb.toString();
    }
}
