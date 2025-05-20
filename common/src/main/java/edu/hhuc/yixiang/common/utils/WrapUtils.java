package edu.hhuc.yixiang.common.utils;

import java.util.function.Function;

/**
 * @author yixiang
 */
public class WrapUtils {
    public static <T, R> R wrap(T target, Function<? super T, ? extends R> mapper, R defaultValue) {
        return target == null ? defaultValue : wrap(mapper.apply(target), defaultValue);
    }

    public static <T> T wrap(T target, T defaultValue) {
        return target == null ? defaultValue : target;
    }
}