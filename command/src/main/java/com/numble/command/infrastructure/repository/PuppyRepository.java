package com.numble.command.infrastructure.repository;

import com.numble.core.domain.Puppy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PuppyRepository extends JpaRepository<Puppy, Long> {

}
