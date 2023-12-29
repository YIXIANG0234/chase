package edu.hhuc.yixiang.service.app.controller;

import edu.hhuc.yixiang.common.annotation.LogRecord;
import edu.hhuc.yixiang.common.enums.OperatorModuleEnum;
import edu.hhuc.yixiang.common.enums.OperatorTypeEnum;
import edu.hhuc.yixiang.service.core.LogRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author guwanghuai
 * @version 1.0
 * @project chase
 * @description
 * @date 2023/12/29 16:55:07
 */
@RestController
@RequestMapping("/log")
public class LogRecordController {
    @Autowired
    private LogRecordService logRecordService;
    @GetMapping("/test")
    @LogRecord(operatorModule = OperatorModuleEnum.SYSTEM, operatorType = OperatorTypeEnum.EXECUTE, content = "controller层的日志：{{#methodResult}}")
    public String doSomething(String value) {
        return logRecordService.doSomething(value);
    }

    @GetMapping("/nothing")
    @LogRecord(operatorModule = OperatorModuleEnum.SYSTEM, operatorType = OperatorTypeEnum.EXECUTE, content = "单纯记录个日志吧")
    public String doNothing(String value) {
        return "server echo : " + value;
    }
}
