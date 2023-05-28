package com.numble.command.infrastructure.repository;

import com.numble.core.domain.Vote;
import com.numble.core.domain.VoteKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepository extends JpaRepository<Vote, VoteKey> {

    Boolean existsByVoteKey(VoteKey voteKey);

}
