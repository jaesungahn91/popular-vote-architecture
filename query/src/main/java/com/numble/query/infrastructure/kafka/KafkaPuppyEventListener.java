package com.numble.query.infrastructure.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.numble.core.domain.Puppy;
import com.numble.core.domain.PuppyCreatedEvent;
import com.numble.query.infrastructure.redis.PuppyRedisRepository;
import com.numble.query.infrastructure.repository.PuppyJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Slf4j
@Component
public class KafkaPuppyEventListener {

    private final PuppyJpaRepository puppyJpaRepository;
    private final PuppyRedisRepository puppyRedisRepository;
    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "${message.topic.name}")
    public void listen(ConsumerRecord<String, String> stringStringConsumerRecord) throws Exception {

        PuppyCreatedEvent event = objectMapper.readValue(stringStringConsumerRecord.value(), PuppyCreatedEvent.class);

        Puppy puppy = puppyJpaRepository.findById(event.getPuppyId())
                .orElseThrow(NoSuchElementException::new);

        puppyRedisRepository.save(puppy);
        log.info("><>< : {}", puppy.toString());

    }

}
