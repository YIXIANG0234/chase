package edu.hhuc.yixiang.service.core.log;

import edu.hhuc.yixiang.common.annotation.LogRecord;
import edu.hhuc.yixiang.common.dto.OperationLogDTO;
import edu.hhuc.yixiang.common.utils.DateUtil;
import edu.hhuc.yixiang.common.utils.IPUtil;
import edu.hhuc.yixiang.service.context.LogRecordContext;
import edu.hhuc.yixiang.service.core.LogRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

/**
 * @author guwanghuai
 * @version 1.0
 * @project chase
 * @description
 * @date 2024/8/14 16:19:12
 */
@Component
public class LogAccessor {
    @Autowired
    private LogRecordService logRecordService;

    public void persist(Map<String, String> expressionParseResult, LogRecord logRecord) {
        Date startTime = (Date) LogRecordContext.getVariable("startTime");
        Date endTime = (Date) LogRecordContext.getVariable("endTime");
        Integer logDuration = (Integer) LogRecordContext.getVariable("logDuration");
        String operatorThread = (String) LogRecordContext.getVariable("operatorThread");
        OperationLogDTO operationLog = OperationLogDTO.builder()
                .operatorUser(expressionParseResult.get(logRecord.operatorUser()))
                .content(expressionParseResult.get(logRecord.content()))
                .businessId(expressionParseResult.get(logRecord.businessId()))
                .operatorModule(logRecord.operatorModule().getCode())
                .operatorType(logRecord.operatorType().getCode())
                .logDuration(logDuration)
                .operatorThread(operatorThread)
                .ip(IPUtil.getClientIp())
                .startTime(startTime)
                .endTime(endTime)
                .duration((int) DateUtil.durationBetween(startTime, endTime))
                .build();
        logRecordService.recordOperation(operationLog);
    }
}
