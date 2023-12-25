package edu.hhuc.yixiang.service.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author guwanghuai
 * @version 1.0
 * @project chase
 * @description
 * @date 2023/12/24 11:15:05
 */
@ComponentScan(value = {"edu.hhuc.yixiang"})
@SpringBootApplication
public class ChaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChaseApplication.class, args);
    }

}
