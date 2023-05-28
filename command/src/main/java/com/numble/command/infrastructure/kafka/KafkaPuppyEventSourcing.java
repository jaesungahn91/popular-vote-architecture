package com.numble.command.infrastructure.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.numble.command.domain.PuppyEventSourcing;
import com.numble.core.domain.Event;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Slf4j
@RequiredArgsConstructor
@Component
public class KafkaPuppyEventSourcing implements PuppyEventSourcing {

    private final ObjectMapper objectMapper;

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Value("${message.topic.name}")
    private String topicName;

    public void send(Event event) throws JsonProcessingException {
        var json = objectMapper.writeValueAsString(event);
        CompletableFuture<SendResult<String, String>> future = kafkaTemplate.send(topicName, json);
        future.whenComplete((result, ex) -> {
            if (ex == null) {
                log.info("Send json '{}' to topic {}", json, topicName);
            }
            else {
                log.error("Send fail json '{}' to topic {}", json, topicName);
            }
        });
    }

}
