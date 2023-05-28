package com.numble.query.infrastructure.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.numble.core.domain.*;
import com.numble.query.domain.puppy.PuppyModel;
import com.numble.query.infrastructure.redis.PuppyRedisRepository;
import com.numble.query.infrastructure.repository.PuppyJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;
import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
@Component
public class KafkaPuppyEventListener {

    private final PuppyJpaRepository puppyJpaRepository;
    private final PuppyRedisRepository puppyRedisRepository;
    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "${message.topic.name}")
    public void listen(ConsumerRecord<String, String> stringStringConsumerRecord) throws Exception {

        Event event = objectMapper.readValue(stringStringConsumerRecord.value(), Event.class);

        if (event.getEventType().equals(EventType.PUPPY_CREATED)) {

            PuppyCreatedEvent createdEvent = objectMapper.readValue(stringStringConsumerRecord.value(), PuppyCreatedEvent.class);
            Puppy puppy = puppyJpaRepository.findById(createdEvent.getPuppyId())
                    .orElseThrow(NoSuchElementException::new);
            puppyRedisRepository.save(PuppyModel.fromPuppy(puppy));

        } else if (event.getEventType().equals(EventType.VOTE_CREATED)) {

            VoteCreatedEvent createdEvent = objectMapper.readValue(stringStringConsumerRecord.value(), VoteCreatedEvent.class);
            PuppyModel puppyModel = puppyRedisRepository.findById(createdEvent.getPuppyId())
                    .orElseThrow(NoSuchElementException::new);
            puppyModel.increaseVoteCount();
            puppyRedisRepository.save(puppyModel);

        } else if (event.getEventType().equals(EventType.VOTE_DELETED)) {

            VoteDeletedEvent deletedEvent = objectMapper.readValue(stringStringConsumerRecord.value(), VoteDeletedEvent.class);
            PuppyModel puppyModel = puppyRedisRepository.findById(deletedEvent.getPuppyId())
                    .orElseThrow(NoSuchElementException::new);
            puppyModel.decreaseVoteCount();
            puppyRedisRepository.save(puppyModel);

        }


    }

}
