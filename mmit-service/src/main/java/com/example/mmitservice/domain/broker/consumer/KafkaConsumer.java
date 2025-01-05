package com.example.mmitservice.domain.broker.consumer;

import com.example.mmitcommon.dto.TaskDto;
import lombok.Getter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@Getter
@Configuration
public class KafkaConsumer {
    private final List<String> consumerList = new ArrayList<>();

    @Bean
    public Consumer<TaskDto> receiveMessage(){
        return taskDto -> {
            switch (taskDto.taskType()){
                case CLIENT_SERVICE_MESSAGE_ADD -> {
                    String payload = (String) taskDto.payload();
                    consumerList.add(payload);
                }
                case CLIENT_SERVICE_MESSAGE_DELETE -> {
                    consumerList.remove(consumerList.size() - 1);
                }
            }
        };
    }
}
