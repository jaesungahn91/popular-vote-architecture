package com.numble.command.application.puppy;

import jakarta.validation.constraints.NotBlank;

public record PuppyPostRequestDTO(@NotBlank String name,
                                  @NotBlank String image,
                                  @NotBlank String description) {

}
