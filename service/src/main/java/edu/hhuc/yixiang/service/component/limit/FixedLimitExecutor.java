package edu.hhuc.yixiang.service.component.limit;

import edu.hhuc.yixiang.common.enums.LimitExecutorEnum;
import edu.hhuc.yixiang.common.enums.ScriptEnum;
import edu.hhuc.yixiang.service.component.LuaScriptFactory;
import edu.hhuc.yixiang.service.helper.RedisHelper;
import org.springframework.stereotype.Component;

import java.util.Collections;

/**
 * @author guwanghuai
 * @version 1.0
 * @project chase
 * @description
 * @date 2024/1/8 21:45:13
 */
@Component
public class FixedLimitExecutor implements LimitExecutor {

    @Override
    public LimitExecutorEnum getExecutorType() {
        return LimitExecutorEnum.FIXED;
    }

    @Override
    public boolean acquire(RateLimitConfiguration configuration) {
        Long count = (Long) RedisHelper.executeScrpit(LuaScriptFactory.getScript(ScriptEnum.FIXED_LIMIT_SCRIPT), Collections.singletonList(configuration.getKey()), configuration.getWindow());
        return count <= configuration.getThreshold();
    }
}
