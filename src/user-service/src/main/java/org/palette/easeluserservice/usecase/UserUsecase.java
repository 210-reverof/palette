package org.palette.easeluserservice.usecase;

import lombok.RequiredArgsConstructor;
import org.palette.easeluserservice.api.dto.response.UsernameVerifyResponse;
import org.palette.easeluserservice.persistence.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class UserUsecase {

    public UsernameVerifyResponse sampleVerify(String username) {
        return new UsernameVerifyResponse(true);
    }

    public UsernameVerifyResponse executeVerify(String username) {
        return new UsernameVerifyResponse(false);
    }
}
