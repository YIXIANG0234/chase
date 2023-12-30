package edu.hhuc.yixiang.service.app.controller;

import edu.hhuc.yixiang.common.annotation.LogRecord;
import edu.hhuc.yixiang.common.base.PageResponse;
import edu.hhuc.yixiang.common.base.SortRequest;
import edu.hhuc.yixiang.common.dto.OperationLogDTO;
import edu.hhuc.yixiang.common.enums.OperatorModuleEnum;
import edu.hhuc.yixiang.common.enums.OperatorTypeEnum;
import edu.hhuc.yixiang.service.core.LogRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/page")
    @LogRecord(operatorModule = OperatorModuleEnum.SYSTEM, operatorType = OperatorTypeEnum.EXECUTE, content = "查询日志列表，数据总数：{{#_result.totalRow}}，总页数：{{#_result.totalPage}}，当前页：{{#request.page}}")
    public PageResponse<OperationLogDTO> page(@RequestBody SortRequest<OperationLogDTO> request) {
        return logRecordService.page(request);
    }
}
