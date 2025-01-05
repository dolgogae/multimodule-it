package com.example.mmitclient.domain.broker.producer;

import com.example.mmitcommon.dto.TaskDto;
import com.example.mmitcommon.enums.TaskType;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaProducer {
    private final StreamBridge streamBridge;

    public boolean sendMessage(){
        TaskDto taskDto = TaskDto.builder()
                .taskType(TaskType.CLIENT_SERVICE_MESSAGE_ADD)
                .payload("message")
                .build();
        return streamBridge.send("sendService-out-0", taskDto);
    }
}
