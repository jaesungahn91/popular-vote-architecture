package com.numble.query.domain.puppy;


import com.numble.core.domain.Puppy;
import org.springframework.data.redis.core.RedisHash;

@RedisHash
public record PuppyModel(Long id, String name, String image, String description, Integer voteCount) {

    public static PuppyModel of(Puppy puppy, Integer voteCount) {
        return new PuppyModel(puppy.getId(),
                puppy.getName(),
                puppy.getImage(),
                puppy.getDescription(),
                voteCount);
    }

    public static PuppyModel fromPuppy(Puppy puppy) {
        return new PuppyModel(puppy.getId(),
                puppy.getName(),
                puppy.getImage(),
                puppy.getDescription(),
                0);
    }

}