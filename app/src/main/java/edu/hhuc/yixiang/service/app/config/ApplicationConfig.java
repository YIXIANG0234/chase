package edu.hhuc.yixiang.service.app.config;

import edu.hhuc.yixiang.service.lifecycle.LifeCycleBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author guwanghuai
 * @version 1.0
 * @project chase
 * @description
 * @date 2024/9/5 16:16:42
 */
@Configuration
public class ApplicationConfig {

    @Bean(initMethod = "initMethod", destroyMethod = "destroyMethod")
    public LifeCycleBean lifeCycleBean() {
        return new LifeCycleBean();
    }
}
