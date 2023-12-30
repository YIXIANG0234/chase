package edu.hhuc.yixiang.service.core.impl;

import com.mybatisflex.core.paginate.Page;
import edu.hhuc.yixiang.common.annotation.LogRecord;
import edu.hhuc.yixiang.common.base.PageResponse;
import edu.hhuc.yixiang.common.base.SortRequest;
import edu.hhuc.yixiang.common.base.Sorter;
import edu.hhuc.yixiang.common.dto.OperationLogDTO;
import edu.hhuc.yixiang.common.entity.OperationLog;
import edu.hhuc.yixiang.common.enums.OperatorModuleEnum;
import edu.hhuc.yixiang.common.enums.OperatorTypeEnum;
import edu.hhuc.yixiang.repository.dao.impl.OperationLogDaoImpl;
import edu.hhuc.yixiang.service.context.LogRecordContext;
import edu.hhuc.yixiang.service.core.LogRecordService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author guwanghuai
 * @version 1.0
 * @project chase
 * @description
 * @date 2023/12/29 16:53:04
 */
@Service
public class LogRecordServiceImpl implements LogRecordService {

    @Autowired
    private OperationLogDaoImpl operationLogDao;

    @Override
    @LogRecord(operatorType = OperatorTypeEnum.EXECUTE, operatorModule = OperatorModuleEnum.SYSTEM, content = "目标方法执行前自定义函数结果为：{GenericIParseFunction.before{#value}} => 目标方法执行后自定义函数结果为：{GenericIParseFunction{#suffix, #value}}", businessId = "test123")
    public String doSomething(String value) {
        LogRecordContext.putVariable("suffix", "input value of this method is : " + value);
        try {
            Thread.sleep(value.length() * 10L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return value.toUpperCase();
    }

    @Override
    public void recordOperation(OperationLogDTO operationLogDTO) {
        operationLogDao.saveOrUpdate(operationLogDTO);
    }

    @Override
    public PageResponse<OperationLogDTO> page(SortRequest<OperationLogDTO> request) {
        Sorter sorter = Sorter.defaultSorter(request.getSorter(), new HashMap<>());
        Page<OperationLog> page = operationLogDao.page(request.getFilter(), sorter);
        List<OperationLogDTO> list = page.getRecords().stream().map(x -> {
            OperationLogDTO dto = new OperationLogDTO();
            BeanUtils.copyProperties(x, dto);
            return dto;
        }).collect(Collectors.toList());
        return PageResponse.of(list, page.getTotalPage(), page.getTotalRow());
    }
}
