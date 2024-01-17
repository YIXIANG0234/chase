package edu.hhuc.yixiang.common.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @author guwanghuai
 * @version 1.0
 * @project chase
 * @description
 * @date 2024/1/10 11:18:25
 */
@Data
public class ServiceCallDTO {
    @NotBlank(message = "服务名称不能为空")
    private String serviceName;
    private String methodName;
    private ServiceCallParamDTO[] params;
}
