package edu.hhuc.yixiang.service.component;

import edu.hhuc.yixiang.common.enums.ScriptEnum;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author guwanghuai
 * @version 1.0
 * @project chase
 * @description
 * @date 2024/1/10 21:59:14
 */
@Component
public class LuaScriptFactory {
    private static final Map<ScriptEnum, RedisScript<?>> SCRIPTS = new ConcurrentHashMap<>(16);

    public static final String SCRIPTS_PATH_BASE = "scripts/lua/";

    public static RedisScript<?> getScript(ScriptEnum script) {
        if (!SCRIPTS.containsKey(script)) {
            // GWH TODO: 2024/1/11 这个提醒代表啥呢：Synchronization on method parameter 'script'
            synchronized (script) {
                if (!SCRIPTS.containsKey(script)) {
                    RedisScript<?> redisScript = RedisScript.of(new ClassPathResource(SCRIPTS_PATH_BASE + script.getFileName()), script.getResultType());
                    SCRIPTS.put(script, redisScript);
                }
            }
        }
        return SCRIPTS.get(script);
    }
}
