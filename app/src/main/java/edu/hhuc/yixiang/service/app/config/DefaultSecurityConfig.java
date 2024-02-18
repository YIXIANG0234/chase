package edu.hhuc.yixiang.service.app.config;

import edu.hhuc.yixiang.service.filters.ChaseFirstFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;

/**
 * @author guwanghuai
 * @version 1.0
 * @project chase
 * @description
 * @date 2024/1/22 17:51:58
 */
@Configuration
@EnableMethodSecurity
public class DefaultSecurityConfig {

    /**
     * 配置自定义filter，也可以直接在类上加@Component注解进行配置
     *
     * @param httpSecurity
     * @return
     * @throws Exception
     */
    @Bean
    SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                // 添加过滤器
                .addFilterBefore(new ChaseFirstFilter(), AuthorizationFilter.class)
                // 设置所有请求都需要认证
                .authorizeHttpRequests((auth) -> {
                    auth.anyRequest().authenticated();
                })
                // 设置允许跨域访问
                .cors(Customizer.withDefaults())
                // 表单登陆
                .formLogin(Customizer.withDefaults())
        ;
        return httpSecurity.build();
    }
}
