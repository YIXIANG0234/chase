package edu.hhuc.yixiang.service.core;

import edu.hhuc.yixiang.common.base.PageResponse;
import edu.hhuc.yixiang.common.base.SortRequest;
import edu.hhuc.yixiang.common.dto.OperationLogDTO;

/**
 * @author guwanghuai
 * @version 1.0
 * @project chase
 * @description
 * @date 2023/12/29 16:52:23
 */
public interface LogRecordService {
    String doSomething(String value);

    void recordOperation(OperationLogDTO operationLogDTO);

    PageResponse<OperationLogDTO> page(SortRequest<OperationLogDTO> request);
}
