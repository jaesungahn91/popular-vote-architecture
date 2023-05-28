package com.numble.command.application.puppy;


import com.numble.core.domain.Puppy;

public record PuppyModel(String name, String image, String description) {

    public static PuppyModel fromPuppy(Puppy puppy) {
        return new PuppyModel(puppy.getName(), puppy.getImage(), puppy.getDescription());
    }

}
