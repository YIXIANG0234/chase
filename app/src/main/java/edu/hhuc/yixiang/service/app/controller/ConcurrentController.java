package edu.hhuc.yixiang.service.app.controller;

import edu.hhuc.yixiang.common.base.BaseResponse;
import edu.hhuc.yixiang.common.dto.SystemMetric;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.startup.Tomcat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.embedded.tomcat.TomcatWebServer;
import org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author guwanghuai
 * @version 1.0
 * @project chase
 * @description
 * @date 2024/8/22 17:30:21
 */
@RestController
@RequestMapping("/concurrent")
@Slf4j
public class ConcurrentController {

    @Autowired
    ServletWebServerApplicationContext applicationContext;

    @GetMapping("/maxThread")
    public BaseResponse<String> maxThread() {
        Tomcat tomcat = ((TomcatWebServer) applicationContext.getWebServer()).getTomcat();
        String info = tomcat.getConnector().getProtocolHandler().getExecutor().toString();
        info = info.substring(info.indexOf("["));
        log.info(info);
        try {
            TimeUnit.HOURS.sleep(1L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return BaseResponse.ofSuccess(info);
    }

    @GetMapping("/cpuCore")
    public BaseResponse<String> cpuCore() {
        Runtime runtime = Runtime.getRuntime();
        SystemMetric systemMetric = new SystemMetric();
        systemMetric.setAvailableProcessors(runtime.availableProcessors());
        systemMetric.setFreeMemory(runtime.freeMemory());
        systemMetric.setMaxMemory(runtime.maxMemory());
        systemMetric.setTotalMemory(runtime.totalMemory());
        return BaseResponse.ofSuccess(systemMetric.memoryForMB());
    }
}
