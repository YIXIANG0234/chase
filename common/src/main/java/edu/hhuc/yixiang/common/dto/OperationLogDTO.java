package edu.hhuc.yixiang.common.dto;

import lombok.*;

import java.util.Date;

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
    private Date createdAt;
}
