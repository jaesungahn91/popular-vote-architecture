package com.numble.command.domain;

import com.numble.command.infrastructure.repository.PuppyRepository;
import com.numble.command.infrastructure.repository.VoteRepository;
import com.numble.core.domain.*;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class PuppyService {

    private final PuppyRepository puppyRepository;
    private final VoteRepository voteRepository;
    private final ApplicationEventPublisher applicationEventPublisher;

    @Transactional
    public Puppy createPuppy(String name, String image, String description) {
        var puppyCreated = puppyRepository.save(Puppy.of(name, image, description));
        applicationEventPublisher.publishEvent(new PuppyCreatedEvent(puppyCreated.getId()));
        return puppyCreated;
    }

    @Transactional
    public void votePuppy(long id, String ip) {
        Puppy puppy = puppyRepository.findById(id).orElseThrow(NoSuchElementException::new);
        if (!voteRepository.existsByVoteKey(new VoteKey(ip, puppy))) {
            var voteCreated = voteRepository.save(Vote.of(ip, puppy));
            applicationEventPublisher.publishEvent(new VoteCreatedEvent(voteCreated.getPuppyId()));
        }
    }

    @Transactional
    public void unvotePuppy(long id, String ip) {
        Puppy puppy = puppyRepository.findById(id).orElseThrow(NoSuchElementException::new);
        if (voteRepository.existsByVoteKey(new VoteKey(ip, puppy))) {
            voteRepository.delete(Vote.of(ip, puppy));
            applicationEventPublisher.publishEvent(new VoteDeletedEvent(id));
        }
    }

}
