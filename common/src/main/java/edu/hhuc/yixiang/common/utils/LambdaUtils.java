package edu.hhuc.yixiang.common.utils;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;
import java.util.Set;
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

    public static <I, T> List<I> distinct(List<I> list, Function<? super I, ? extends T> mapper) {
        if (isEmpty(list)) {
            return Lists.newArrayList();
        }
        return list.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.collectingAndThen(Collectors.toMap(mapper, Function.identity(), (v1, v2) -> v1, LinkedHashMap::new),
                        result -> new ArrayList<>(result.values())));
    }
    public static <T> List<T> intersection(List<T> list1, List<T> list2) {
        if (isEmpty(list1) || isEmpty(list2)) {
            return new ArrayList<>();
        }
        Set<T> set = new HashSet<>(list2);
        return list1.stream()
                .filter(set::contains)
                .collect(Collectors.toList());
    }

    public static <T> boolean isEmpty(Collection<T> list) {
        return list == null || list.isEmpty();
    }
}
