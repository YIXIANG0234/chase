package edu.hhuc.yixiang.service.app.config;

import edu.hhuc.yixiang.common.constant.KafkaConstants;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.RoundRobinPartitioner;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;
import java.util.Properties;

/**
 * 手动配置kafka生产者，消费者
 *
 * @author guwanghuai
 * @version 1.0
 * @project chase
 * @description
 * @date 2024/11/27 14:37:25
 */
@Configuration
public class KafkaConfig {

    @Bean
    public KafkaProducer<String, String> kafkaProducer() {
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        properties.put(ProducerConfig.ACKS_CONFIG, "all");
        properties.put(ProducerConfig.RETRIES_CONFIG, 3);
        properties.put(ProducerConfig.BATCH_SIZE_CONFIG, 5);
        properties.put(ProducerConfig.LINGER_MS_CONFIG, 500);
        properties.put(ProducerConfig.PARTITIONER_CLASS_CONFIG, RoundRobinPartitioner.class.getName());

        return new KafkaProducer<>(properties, new StringSerializer(), new StringSerializer());
    }

    @Bean
    public KafkaConsumer<String, String> kafkaConsumer() {
        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<>(consumerProperties(), new StringDeserializer(), new StringDeserializer());
        kafkaConsumer.subscribe(Collections.singleton(KafkaConstants.FIRST_KAFKA_TOPIC));
        return kafkaConsumer;
    }

    @Bean
    public KafkaConsumer<String, String> assignedConsumer() {
        return new KafkaConsumer<>(consumerProperties(), new StringDeserializer(), new StringDeserializer());
    }

    private Properties consumerProperties() {
        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "custom-consumer");
        properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
        properties.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, "5");
        properties.put(ConsumerConfig.MAX_POLL_INTERVAL_MS_CONFIG, 5 * 60 * 100);
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        return properties;
    }

}
