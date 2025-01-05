package com.example.mmitintegrationtest.clientservice;

import com.example.mmitclient.domain.broker.producer.KafkaProducer;
import com.example.mmitcommon.dto.TaskDto;
import com.example.mmitcommon.enums.TaskType;
import com.example.mmitservice.domain.broker.consumer.KafkaConsumer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;

import java.util.List;

@SpringBootTest
@EmbeddedKafka(partitions = 3,
        brokerProperties = {
                "listeners=PLAINTEXT://localhost:9092"
        },
        ports = { 9092 })
public class ClientToServiceTest {
    @Autowired
    KafkaConsumer kafkaConsumer;
    @Autowired
    KafkaProducer kafkaProducer;

    @Test
    void messageTest() throws InterruptedException {
        TaskInfo taskInfo = TaskInfo.builder()
                .name("message")
                .build();
        TaskDto taskDto = TaskDto.builder()
                .taskType(TaskType.CLIENT_SERVICE_MESSAGE_ADD)
                .payload("message")
                .build();

        kafkaProducer.sendMessage(taskDto);
        Thread.sleep(3000);

        List<String> result = kafkaConsumer.getConsumerList();
        System.out.println(result.size());
    }

    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    static class TaskInfo{
        private String name;
    }
}
