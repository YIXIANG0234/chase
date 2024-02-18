package edu.hhuc.yixiang.service.component.limit;

import edu.hhuc.yixiang.common.enums.LimitExecutorEnum;
import edu.hhuc.yixiang.common.enums.ScriptEnum;
import edu.hhuc.yixiang.service.component.LuaScriptFactory;
import edu.hhuc.yixiang.service.helper.RedisHelper;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Objects;

/**
 * @author guwanghuai
 * @version 1.0
 * @project chase
 * @description
 * @date 2024/1/10 17:41:48
 */
@Component
public class TokenBucketLimitExecutor implements LimitExecutor {
    @Override
    public LimitExecutorEnum getExecutorType() {
        return LimitExecutorEnum.TOKEN;
    }

    @Override
    public boolean acquire(RateLimitConfiguration configuration) {
        Long result = (Long) RedisHelper.executeScrpit(LuaScriptFactory.getScript(ScriptEnum.TOKEN_BUCKET_LIMIT_SCRIPT), Collections.singletonList(configuration.getKey()), configuration.getThreshold(), configuration.getRate(), System.currentTimeMillis());
        return Objects.equals(result, 1L);
    }
}
