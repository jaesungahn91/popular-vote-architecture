package com.numble.query.application.puppy;


import com.numble.core.domain.Puppy;

public record PuppyModel(Long id, String name, String image, String description) {

    public static PuppyModel fromPuppy(Puppy puppy) {
        return new PuppyModel(puppy.getId(),
                puppy.getName(),
                puppy.getImage(),
                puppy.getDescription());
    }

}