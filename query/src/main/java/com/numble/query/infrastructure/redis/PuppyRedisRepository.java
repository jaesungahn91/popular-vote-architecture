package com.numble.query.infrastructure.redis;

import com.numble.core.domain.Puppy;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PuppyRedisRepository extends CrudRepository<Puppy, Long> {

    @Override
    List<Puppy> findAll();

}
