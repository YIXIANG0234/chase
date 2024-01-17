package edu.hhuc.yixiang.service.component.limit;

import edu.hhuc.yixiang.common.enums.LimitExecutorEnum;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author guwanghuai
 * @version 1.0
 * @project chase
 * @description
 * @date 2024/1/8 22:13:18
 */
@Component
public class LimitExecutorFactory {
    private final Map<LimitExecutorEnum, LimitExecutor> executorMap = new ConcurrentHashMap<>(16);

    public LimitExecutorFactory(List<LimitExecutor> executors) {
        if (CollectionUtils.isNotEmpty(executors)) {
            executors.forEach(x -> executorMap.put(x.getExecutorType(), x));
        }
    }

    public LimitExecutor getExecutor(LimitExecutorEnum executorType) {
        return executorMap.get(executorType);
    }
}
