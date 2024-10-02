package edu.hhuc.yixiang.service.app;

import edu.hhuc.yixiang.service.lifecycle.LifeCycleBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author guwanghuai
 * @version 1.0
 * @project chase
 * @description
 * @date 2023/12/24 11:15:05
 */
@ComponentScan(value = {"edu.hhuc.yixiang"})
@MapperScan(value = "edu.hhuc.yixiang.common.mapper")
// 禁用Security
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class ChaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChaseApplication.class, args);
    }

}
