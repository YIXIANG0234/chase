package edu.hhuc.yixiang.service.core;

import edu.hhuc.yixiang.common.dto.AnyInputDTO;
import edu.hhuc.yixiang.common.dto.OperationLogDTO;
import edu.hhuc.yixiang.common.dto.UserDetailDTO;

/**
 * @author guwanghuai
 * @version 1.0
 * @project chase
 * @description
 * @date 2024/8/29 14:43:50
 */
public interface CacheService {
    String randomCode();

    OperationLogDTO queryOperation(long id);

    UserDetailDTO queryUser(AnyInputDTO inputDTO);
}
