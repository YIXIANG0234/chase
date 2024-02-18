package edu.hhuc.yixiang.service.core;

import edu.hhuc.yixiang.common.dto.ServiceCallDTO;

/**
 * @author guwanghuai
 * @version 1.0
 * @project chase
 * @description
 * @date 2024/1/10 11:20:16
 */
public interface ProcessorService {
    Object call(ServiceCallDTO serviceCallDTO);
}
