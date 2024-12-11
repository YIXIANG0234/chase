package edu.hhuc.yixiang.common.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @author guwanghuai
 * @version 1.0
 * @project chase
 * @description
 * @date 2024/11/27 14:53:46
 */
@Data
public class KafkaMessageDTO {
    @NotBlank(message = "消息发送的生产者类型不能为空")
    private String producerType;

    @NotBlank(message = "消息内容不能为空")
    private String message;
}
