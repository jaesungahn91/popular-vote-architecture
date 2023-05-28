package com.numble.query.infrastructure.repository;

import com.numble.core.domain.Puppy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PuppyJpaRepository extends JpaRepository<Puppy, Long> {
}
