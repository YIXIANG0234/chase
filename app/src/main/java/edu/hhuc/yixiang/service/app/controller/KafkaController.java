package edu.hhuc.yixiang.service.app.controller;

import edu.hhuc.yixiang.common.constant.KafkaConstants;
import edu.hhuc.yixiang.common.dto.KafkaMessageDTO;
import edu.hhuc.yixiang.common.dto.TopicSeekDTO;
import edu.hhuc.yixiang.common.enums.KafkaProducerType;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.TopicPartition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author guwanghuai
 * @version 1.0
 * @project chase
 * @description
 * @date 2024/11/25 14:22:41
 */
@Slf4j
@RestController
@RequestMapping("/kafka")
public class KafkaController {
    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;
    @Autowired
    private KafkaProducer<String, String> kafkaProducer;
    @Autowired
    private KafkaConsumer<String, String> kafkaConsumer;
    @Autowired
    private KafkaConsumer<String, String> assignedConsumer;

    @PostMapping("/producer")
    public void producer(@RequestBody KafkaMessageDTO input) {
        if (KafkaProducerType.KAFKA_TEMPLATE.getCode().equals(input.getProducerType())) {
            kafkaTemplate.send(KafkaConstants.FIRST_KAFKA_TOPIC, String.format("message from kafka template：【%s】", input.getMessage()));
        } else {
            ProducerRecord<String, String> producerRecord = new ProducerRecord<>(KafkaConstants.FIRST_KAFKA_TOPIC, String.format("message from kafka producer：【%s】", input.getMessage()));
            kafkaProducer.send(producerRecord);
        }
    }

    @GetMapping("/consumer")
    public void consumer() {
        ConsumerRecords<String, String> consumerRecords = kafkaConsumer.poll(Duration.ofMinutes(3));
        processMessage(consumerRecords);
        kafkaConsumer.commitSync();
    }

    @PostMapping("/seek")
    public void seek(@RequestBody TopicSeekDTO seekDTO) {
        if (BooleanUtils.isTrue(seekDTO.getReset()) && StringUtils.isNotBlank(seekDTO.getTopic()) && CollectionUtils.isNotEmpty(seekDTO.getPartitions())) {
            List<TopicPartition> topicPartitions = seekDTO.getPartitions().stream().map(x -> new TopicPartition(seekDTO.getTopic(), x.getPartition())).collect(Collectors.toList());
            assignedConsumer.assign(topicPartitions);
            seekDTO.getPartitions().forEach(partition -> {
                TopicPartition topicPartition = new TopicPartition(seekDTO.getTopic(), partition.getPartition());
                assignedConsumer.seek(topicPartition, partition.getOffset());
            });
        }
        ConsumerRecords<String, String> consumerRecords = assignedConsumer.poll(Duration.ofSeconds(3));
        processMessage(consumerRecords);
        assignedConsumer.commitSync();
    }

    private void processMessage(ConsumerRecords<String, String> consumerRecords) {
        for (ConsumerRecord<String, String> record : consumerRecords) {
            log.info("{} topic：{}，partition：{}，message：{}，key：{}，offset：{}", kafkaConsumer.groupMetadata().groupId(), record.topic(), record.partition(), record.value(), record.key(), record.offset());
        }
    }
}
