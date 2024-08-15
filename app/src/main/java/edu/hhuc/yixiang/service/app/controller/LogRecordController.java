package edu.hhuc.yixiang.service.app.controller;

import edu.hhuc.yixiang.common.annotation.LogRecord;
import edu.hhuc.yixiang.common.base.BaseResponse;
import edu.hhuc.yixiang.common.base.PageResponse;
import edu.hhuc.yixiang.common.base.SortRequest;
import edu.hhuc.yixiang.common.dto.AnyInputDTO;
import edu.hhuc.yixiang.common.dto.IdDTO;
import edu.hhuc.yixiang.common.dto.OperationLogDTO;
import edu.hhuc.yixiang.common.enums.OperatorModuleEnum;
import edu.hhuc.yixiang.common.enums.OperatorTypeEnum;
import edu.hhuc.yixiang.service.context.LogRecordContext;
import edu.hhuc.yixiang.service.core.LogRecordService;
import jakarta.validation.Valid;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.UUID;

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

    @PostMapping("/simple")
    @LogRecord(operatorModule = OperatorModuleEnum.SYSTEM, operatorType = OperatorTypeEnum.EXECUTE, content = "当前时刻：%{T(edu.hhuc.yixiang.common.utils.DateUtil).formatNow()}，输入：%{#input?.value}，输出：#{#method_result?.result}")
    public BaseResponse<String> simple(@RequestBody @Valid AnyInputDTO input) {
        String value = Objects.isNull(input) ? "" : input.getValue();
        value = StringUtils.isBlank(value) ? "" : value;
        return BaseResponse.ofSuccess(value.toUpperCase());
    }

    @PostMapping("/find")
    @LogRecord(operatorModule = OperatorModuleEnum.SYSTEM, operatorType = OperatorTypeEnum.EXECUTE, content = "controller查询到的日志内容：【#{#method_result?.result?.content}】")
    public BaseResponse<OperationLogDTO> findOperation(@RequestBody @Valid IdDTO idDTO) {
        return BaseResponse.ofSuccess(logRecordService.findOperation(idDTO));
    }

    @PostMapping("/custom")
    @LogRecord(operatorModule = OperatorModuleEnum.SYSTEM, operatorType = OperatorTypeEnum.EXECUTE, content = "%{#execute('joinFunction', #input.value, T(Math).random()*100)} => #{#execute('joinFunction', T(java.util.UUID).randomUUID())}")
    public BaseResponse<String> custom(@RequestBody @Valid AnyInputDTO input) {
        return BaseResponse.ofSuccess("server echo：" + input.getValue());
    }

    @PostMapping("/read")
    @LogRecord(operatorModule = OperatorModuleEnum.SYSTEM, operatorType = OperatorTypeEnum.EXECUTE, content = "读取到单词：#{#word}")
    public BaseResponse<String> readLocal(@RequestBody @Valid AnyInputDTO input) {
        String word = Objects.nonNull(input) ? input.getValue().toUpperCase() : UUID.randomUUID().toString();
        LogRecordContext.putVariable("word", word);
        return BaseResponse.ofSuccess("server echo：" + word);
    }

    @PostMapping("/page")
    @LogRecord(operatorModule = OperatorModuleEnum.SYSTEM, operatorType = OperatorTypeEnum.EXECUTE, content = "查询日志列表，数据总数：{{#_result.totalRow}}，总页数：{{#_result.totalPage}}，当前页：{{#request.page}}")
    public PageResponse<OperationLogDTO> page(@RequestBody SortRequest<OperationLogDTO> request) {
        return logRecordService.page(request);
    }
}
