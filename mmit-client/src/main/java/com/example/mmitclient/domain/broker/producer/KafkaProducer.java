package com.example.mmitclient.domain.broker.producer;

import com.example.mmitcommon.dto.TaskDto;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaProducer {
    private final StreamBridge streamBridge;

    public boolean sendMessage(TaskDto taskDto){
        return streamBridge.send("sendMessage-out-0", taskDto);
    }
}
