package edu.hhuc.yixiang.common.lang.demo;

import edu.hhuc.yixiang.common.utils.JsonUtil;
import org.apache.commons.collections.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @version 1.0
 * @project chase
 * @description
 * @date 2025/7/18 11:02:17
 */
public class Main {
    public static void main(String[] args) throws Exception {
        long time1 = 1753716648000L;
        long time2 = 0;
        System.out.println(TimeUnit.MILLISECONDS.toDays(System.currentTimeMillis()-time1));
        List<Map> a = JsonUtil.readFromFile("/Users/yixiang/gitwork/yixiang/chase/common/src/main/resources/test.json", List.class);
        List<Long> goodsId1 = a.stream().map(x->(Long)x.get("goodsId")).distinct().collect(Collectors.toList());

        List<Map> b = JsonUtil.readFromFile("/Users/yixiang/gitwork/yixiang/chase/common/src/main/resources/test2.json", List.class);
        List<Long> goodsId2 = b.stream().map(x->(Long)x.get("goodsId")).distinct().collect(Collectors.toList());

        System.out.println(goodsId1);
        System.out.println(goodsId2);

        System.out.println(CollectionUtils.subtract(goodsId2, goodsId1));
    }
}
