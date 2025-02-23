package org.pallete.easelgatewayservice.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ExceptionType {

    // 400
    GATEWAY_400_000001(
            "GATEWAY_400_000001",
            "INCORRECT_PARAMETER",
            "요청 파라미터가 올바르지 않습니다.",
            HttpStatus.BAD_REQUEST
    ),

    // 401
    GATEWAY_401_000001(
            "GATEWAY_401_000001",
            "MISSED AUTHENTICATION",
            "해당 요청은 인증이 필요합니다.",
            HttpStatus.UNAUTHORIZED
    ),

    // 403
    GATEWAY_403_000001(
            "GATEWAY_403_000001",
            "NOT ALLOWED PERMISSION",
            "해당 요청에 대한 권한이 없습니다.",
            HttpStatus.FORBIDDEN
    ),


    // 404
    GATEWAY_404_000001(
            "GATEWAY_404_000001",
            "NOT FOUNDED",
            "해당 리소스가 존재하지 않습니다.",
            HttpStatus.NOT_FOUND
    ),

    // 405
    GATEWAY_405_000001(
            "GATEWAY_405_000001",
            "NOT ALLOWED METHOD",
            "올바르지 않은 요청 메서드입니다.",
            HttpStatus.METHOD_NOT_ALLOWED
    ),

    // 409
    GATEWAY_409_000001(
            "GATEWAY_409_000001",
            "DUPLICATED",
            "중복된 리소스가 있습니다.",
            HttpStatus.CONFLICT
    ),

    GATEWAY_500_000001(
            "GATEWAY_500_000001",
            "INTERNAL_SERVER_ERROR",
            "서버 내부에 예기치 못한 오류가 발생했습니다.",
            HttpStatus.INTERNAL_SERVER_ERROR
    ),


    ;

    private final String code;
    private final String message;
    private final String description;
    private final HttpStatus httpStatus;
}
