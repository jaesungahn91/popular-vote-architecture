package com.numble.query.domain.puppy;

import com.numble.query.infrastructure.redis.PuppyRedisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class PuppyService {

    private final PuppyRedisRepository puppyRedisRepository;

    public List<PuppyModel> getPuppies() {
        return puppyRedisRepository.findAll();
    }

    public PuppyModel getPuppy(long id) {
        return puppyRedisRepository.findById(id)
                .orElseThrow(NoSuchElementException::new);
    }

}
