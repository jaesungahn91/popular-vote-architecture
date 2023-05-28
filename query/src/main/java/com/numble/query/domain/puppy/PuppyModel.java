package com.numble.query.domain.puppy;


import com.numble.core.domain.Puppy;
import lombok.Getter;
import org.springframework.data.redis.core.RedisHash;

@Getter
@RedisHash
public class PuppyModel {

    private Long id;
    private String name;
    private String image;
    private String description;
    private Integer voteCount;

    protected PuppyModel() {
    }

    public PuppyModel(Long id, String name, String image, String description, Integer voteCount) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.description = description;
        this.voteCount = voteCount;
    }

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

    public void increaseVoteCount() {
        this.voteCount += 1;
    }

    public void decreaseVoteCount() {
        if (this.voteCount > 0) {
            this.voteCount -= 1;
        }
    }

}