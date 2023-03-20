package com.pms.osb.common;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ApiError {
    COMMON_SUCCESS(HttpStatus.OK, "000000", "정상적으로 처리되었습니다."),
    NOT_EXIST_BLOG_SEARCH(HttpStatus.BAD_REQUEST, "E10000", "Not Exist Blog"),
    NOT_INPUT_TEXT(HttpStatus.BAD_REQUEST, "E10001", "text parameter required"),
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
