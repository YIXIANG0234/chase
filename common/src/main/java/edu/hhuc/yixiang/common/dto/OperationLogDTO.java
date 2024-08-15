package edu.hhuc.yixiang.common.dto;

import edu.hhuc.yixiang.common.entity.OperationLog;
import lombok.*;
import org.springframework.beans.BeanUtils;

import java.util.Date;
import java.util.Objects;

/**
 * @author guwanghuai
 * @version 1.0
 * @project chase
 * @description
 * @date 2023/12/29 21:00:08
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OperationLogDTO {
    private Long id;
    private String operatorUser;
    private String content;
    private String businessId;
    private String operatorType;
    private String operatorModule;
    private String ip;
    private Date startTime;
    private Date endTime;
    private Integer duration;
    private Integer logDuration;
    private String operatorThread;
    private Date createdAt;

    public static OperationLogDTO convertFrom(OperationLog operationLog) {
        if (Objects.isNull(operationLog)) {
            return null;
        }
        OperationLogDTO dto = new OperationLogDTO();
        BeanUtils.copyProperties(operationLog, dto);
        return dto;
    }
}
