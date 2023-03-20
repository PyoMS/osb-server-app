package com.pms.osb.common;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ApiError {
    COMMON_SUCCESS(HttpStatus.OK, "000000", "정상적으로 처리되었습니다."),
    VALIDATION_EXCEPTION(HttpStatus.BAD_REQUEST, "E10001", "파라미터 정보를 다시 확인해주십시오."),
    HANDLE_BIND_EXCEPTION(HttpStatus.BAD_REQUEST, "E10002", "파라미터의 유효값이 아닙니다."),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "E10004", "해당 API에 권한이 없습니다."),

    NOT_ACCESS_EXTERANL_API_EXCEPTION(HttpStatus.SERVICE_UNAVAILABLE, "E20001", "현재 외부 API에 접속할 수 없습니다."),
    ;

    ApiError(HttpStatus httpStatus, String errorCode, String errorMessage) {
        this.httpStatus = httpStatus;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    private final HttpStatus httpStatus;
    private final String errorCode;
    private final String errorMessage;

}
