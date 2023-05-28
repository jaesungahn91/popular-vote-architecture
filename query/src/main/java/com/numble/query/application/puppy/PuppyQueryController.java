package com.numble.query.application.puppy;

import com.numble.query.domain.puppy.PuppyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "puppy api")
@RequiredArgsConstructor
@RestController
public class PuppyQueryController {

    private final PuppyService puppyService;

    @Operation(summary = "get puppy list")
    @GetMapping("/puppies")
    public List<PuppyModel> getPuppies() {
        return puppyService.getPuppies();
    }

    @Operation(summary = "get puppy")
    @GetMapping("/puppies/{id}")
    public PuppyModel getPuppy(@PathVariable long id) {
        return puppyService.getPuppy(id);
    }

}
