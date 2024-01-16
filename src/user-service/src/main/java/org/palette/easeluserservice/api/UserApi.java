package org.palette.easeluserservice.api;

import lombok.RequiredArgsConstructor;
import org.palette.easeluserservice.api.dto.request.UsernameVerifyRequest;
import org.palette.easeluserservice.api.dto.response.UsernameVerifyResponse;
import org.palette.easeluserservice.usecase.UserUsecase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserApi {

    private final UserUsecase userUsecase;

    @PostMapping("verify-username")
    public ResponseEntity<UsernameVerifyResponse> verifyUsername(
            @RequestBody UsernameVerifyRequest usernameVerifyRequest
    ) {
        return ResponseEntity.ok(
                userUsecase.sampleVerify(usernameVerifyRequest.username())
        );
    }

    @PostMapping("sample")
    public ResponseEntity<Void> verifyUsername(
            @RequestBody UsernameVerifyRequest usernameVerifyRequest
    ) {
        return ResponseEntity.ok(
                userUsecase.sampleVerify(usernameVerifyRequest.username())
        );
    }

}
