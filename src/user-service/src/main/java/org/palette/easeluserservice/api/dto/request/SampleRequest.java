package org.palette.easeluserservice.api.dto.request;

import jakarta.validation.constraints.NotBlank;

public record SampleRequest(

        @NotBlank
        String nickname,

        @NotBlank
        String profileImagePath,

        @NotBlank
        String profileImagePath,
) {
}
