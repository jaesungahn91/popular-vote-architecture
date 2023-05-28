package com.numble.query.domain.puppy;

import com.numble.query.infrastructure.redis.PuppyRedisRepository;
import com.numble.query.infrastructure.repository.PuppyJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor
@Service
public class PuppyService {

    private final PuppyRedisRepository puppyRedisRepository;
    private final PuppyJpaRepository puppyJpaRepository;

    public List<PuppyModel> getPuppies() {
        return puppyRedisRepository.findAll();
    }

    public PuppyModel getPuppy(long id) {
        var puppy = puppyJpaRepository.findById(id)
                .orElseThrow(NoSuchElementException::new);
        return PuppyModel.fromPuppy(puppy);
    }

}
