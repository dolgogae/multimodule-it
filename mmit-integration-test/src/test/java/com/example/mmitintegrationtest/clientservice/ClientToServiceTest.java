package com.example.mmitintegrationtest.clientservice;

import com.example.mmitclient.domain.broker.consumer.ServiceKafkaConsumer;
import com.example.mmitclient.domain.broker.producer.KafkaProducer;
import com.example.mmitcommon.dto.TaskDto;
import com.example.mmitcommon.enums.TaskType;
import com.example.mmitservice.domain.broker.consumer.ClientKafkaConsumer;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.utils.KafkaTestUtils;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;
import java.util.Map;

@SpringBootTest
@SpringJUnitConfig
@EmbeddedKafka(partitions = 1,
        brokerProperties = {
                "listeners=PLAINTEXT://localhost:9092"
        },
        topics = {
            "client-service-topic", "service-client-topic"
        },
        ports = { 9092 })
public class ClientToServiceTest {
    @Autowired
    ClientKafkaConsumer serviceKafkaConsumer;
    @Autowired
    KafkaProducer kafkaProducer;
    @Autowired
    ServiceKafkaConsumer clientServiceKafkaConsumer;

    @Autowired
    EmbeddedKafkaBroker embeddedKafkaBroker;
    @Test
    void messageTest() throws InterruptedException {
        TaskDto taskDto = TaskDto.builder()
                .taskType(TaskType.CLIENT_SERVICE_MESSAGE_ADD)
                .payload("message")
                .build();

        kafkaProducer.sendMessage(taskDto);
        Thread.sleep(3000);

        List<String> serviceResult = serviceKafkaConsumer.getConsumerList();
        Assertions.assertEquals(1,serviceResult.size());

        List<String> clientResult = clientServiceKafkaConsumer.getConsumerList();
        Assertions.assertEquals(0,clientResult.size());
    }

    @Test
    void produceTest() throws InterruptedException {
        TaskDto taskDto = TaskDto.builder()
                .taskType(TaskType.CLIENT_SERVICE_MESSAGE_ADD)
                .payload("message")
                .build();

        kafkaProducer.sendMessage(taskDto);
        Thread.sleep(3000);

        // make embedded consumer
        Map<String, Object> consumerProps = KafkaTestUtils.consumerProps("testGroup", "false", embeddedKafkaBroker);
        ConsumerFactory<String, String> consumerFactory = new DefaultKafkaConsumerFactory<>(consumerProps);
        Consumer<String, String> consumer = consumerFactory.createConsumer();
        embeddedKafkaBroker.consumeFromAnEmbeddedTopic(consumer, "client-service-topic");

        ConsumerRecord<String, String> received = KafkaTestUtils.getSingleRecord(consumer, "client-service-topic");

        System.out.println(received.topic());
        System.out.println(received.value());
    }
}
