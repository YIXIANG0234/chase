package edu.hhuc.yixiang.common.enums;

import lombok.Getter;

/**
 * @author guwanghuai
 * @version 1.0
 * @project chase
 * @description
 * @date 2024/11/27 14:55:38
 */
@Getter
public enum KafkaProducerType {
    KAFKA_TEMPLATE("kafkaTemplate", "使用kafkaTemplate发送消息"),
    KAFKA_PRODUCER("kafkaProducer", "使用原生producer发送消息"),
    ;

    KafkaProducerType(String code, String message) {
        this.code = code;
        this.message = message;
    }

    private final String code;
    private final String message;
}
