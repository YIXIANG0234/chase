package edu.hhuc.yixiang.service.kafka;

import edu.hhuc.yixiang.common.constant.KafkaConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author guwanghuai
 * @version 1.0
 * @project chase
 * @description
 * @date 2024/11/25 20:42:10
 */
@Component
@Slf4j
public class KafkaConsumerListener {
    @KafkaListener(topics = {KafkaConstants.FIRST_KAFKA_TOPIC})
    public void consumer1(ConsumerRecord<String, Object> record) {
        log.info("consumer1 topic：{}，partition：{}，message：{}，key：{}，offset：{}", record.topic(), record.partition(), record.value(), record.key(), record.offset());
    }

    @KafkaListener(topics = {KafkaConstants.FIRST_KAFKA_TOPIC})
    public void consumer2(ConsumerRecord<String, Object> record) {
        log.info("consumer2 topic：{}，partition：{}，message：{}，key：{}，offset：{}", record.topic(), record.partition(), record.value(), record.key(), record.offset());
    }

    // @KafkaListener(topics = {KafkaConstants.TOPIC_CONSUMER_OFFSETS}, properties = {"auto.offset.reset=latest"})
    public void offsetConsumer(ConsumerRecord<String, Object> record) {
        log.info("offsetConsumer topic：{}，partition：{}，message：{}，key：{}，offset：{}", record.topic(), record.partition(), record.value(), record.key(), record.offset());
    }
}
