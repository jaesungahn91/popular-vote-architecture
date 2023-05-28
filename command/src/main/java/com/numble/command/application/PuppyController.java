package com.numble.command.application;

import com.numble.command.domain.PuppyService;
import com.numble.command.infrastructure.util.ClientAddressUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "puppy api")
@RequiredArgsConstructor
@RestController
public class PuppyController {

    private final PuppyService puppyService;

    @Operation(summary = "create puppy")
    @PostMapping("/puppies")
    public PuppyModel postPuppy(@Valid @RequestBody PuppyPostRequestDTO dto) {
        var puppyCreated = puppyService.createPuppy(dto.name(), dto.image(), dto.description());
        return PuppyModel.fromPuppy(puppyCreated);
    }

    @Operation(summary = "puppy vote")
    @PostMapping("/puppies/{id}/vote")
    public void votePuppy(@PathVariable long id,
                          HttpServletRequest request) {
        String ip = ClientAddressUtil.getLocation(request);
        puppyService.votePuppy(id, ip);
    }

    @Operation(summary = "puppy unvote")
    @DeleteMapping("/puppies/{id}/vote")
    public void unvotePuppy(@PathVariable long id,
                            HttpServletRequest request) {
        String ip = ClientAddressUtil.getLocation(request);
        puppyService.unvotePuppy(id, ip);
    }

}
