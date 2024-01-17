package edu.hhuc.yixiang.service.component.limit;

import edu.hhuc.yixiang.common.enums.LimitExecutorEnum;
import edu.hhuc.yixiang.common.enums.ScriptEnum;
import edu.hhuc.yixiang.service.component.LuaScriptFactory;
import edu.hhuc.yixiang.service.helper.RedisHelper;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.UUID;

/**
 * @author guwanghuai
 * @version 1.0
 * @project chase
 * @description
 * @date 2024/1/8 21:47:00
 */
@Component
public class SlideLimitExecutor implements LimitExecutor {

    @Override
    public LimitExecutorEnum getExecutorType() {
        return LimitExecutorEnum.SLIDE;
    }

    @Override
    public boolean acquire(RateLimitConfiguration configuration) {
        Long count = (Long) RedisHelper.executeScrpit(LuaScriptFactory.getScript(ScriptEnum.SLIDE_LIMIT_SCRIPT), Collections.singletonList(configuration.getKey()), configuration.getWindow(), System.currentTimeMillis(), configuration.getThreshold(), UUID.randomUUID().toString());
        return count < configuration.getThreshold();
    }
}
