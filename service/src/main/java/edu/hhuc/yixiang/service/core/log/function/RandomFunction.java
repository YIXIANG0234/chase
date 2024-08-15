package edu.hhuc.yixiang.service.core.log.function;

import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Random;

/**
 * @author guwanghuai
 * @version 1.0
 * @project chase
 * @description
 * @date 2024/8/15 10:53:32
 */
@Component
public class RandomFunction implements IParseFunction<String> {
    @Override
    public String functionName() {
        return "randomFunction";
    }

    @Override
    public String apply(Object... args) {
        if (Objects.isNull(args) || args.length != 2) {
            return "";
        }
        int min = (int) args[0];
        int max = (int) args[1];
        Random random = new Random();
        int length = random.nextInt(min, max);
        StringBuilder word = new StringBuilder();
        for (int i = 0; i < length; i++) {
            word.append((char) randomChar());
        }
        return word.toString();
    }

    private byte randomChar() {
        // 0小写字母1大写字母
        int flag = (int) (Math.random() * 2);
        byte resultBt;
        // 0 <= bt < 26
        byte bt = (byte) (Math.random() * 26);
        if (flag == 0) {
            resultBt = (byte) (65 + bt);
        } else {
            resultBt = (byte) (97 + bt);
        }
        return resultBt;
    }
}
