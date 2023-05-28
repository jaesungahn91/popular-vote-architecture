package com.numble.command.application;

import jakarta.validation.constraints.NotBlank;

public record PuppyPostRequestDTO(@NotBlank String name,
                                  @NotBlank String image,
                                  @NotBlank String description) {

}
