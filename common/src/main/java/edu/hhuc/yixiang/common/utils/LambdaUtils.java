package edu.hhuc.yixiang.common.utils;

import com.google.common.collect.Lists;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author guwanghuai
 * @version 1.0
 * @project chase
 * @description
 * @date 2024/1/29 15:20:20
 */
public class LambdaUtils {
    public static <T, R> List<R> map(List<T> list, Function<? super T, ? extends R> mapper) {
        if (isEmpty(list)) {
            return Lists.newArrayList();
        }
        return list.stream().filter(Objects::nonNull).map(mapper).distinct().collect(Collectors.toList());
    }

    public static <T> boolean isEmpty(Collection<T> list) {
        return list == null || list.isEmpty();
    }
}
