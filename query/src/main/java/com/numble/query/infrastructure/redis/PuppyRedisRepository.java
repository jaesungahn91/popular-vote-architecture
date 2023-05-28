package com.numble.query.infrastructure.redis;

import com.numble.query.domain.puppy.PuppyModel;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface PuppyRedisRepository extends CrudRepository<PuppyModel, Long> {

    @Override
    List<PuppyModel> findAll();

    @Override
    Optional<PuppyModel> findById(Long id);

}
