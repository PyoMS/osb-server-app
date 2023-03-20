package com.pms.osb.common;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ApiError {
    COMMON_SUCCESS(HttpStatus.OK, "000000", "정상적으로 처리되었습니다."),
    NOT_EXIST_BLOG_SEARCH(HttpStatus.BAD_REQUEST, "E10000", "검색어에 해당하는 블로그가 없습니다."),
    VALIDATION_EXCEPTION(HttpStatus.BAD_REQUEST, "E10001", "파라미터 정보를 다시 확인해주십시오."),
    NOT_INPUT_TEXT(HttpStatus.BAD_REQUEST, "E10002", "검색어 파라미터를 입력해주십시오."),
    NOT_ACCESS_API(HttpStatus.NETWORK_AUTHENTICATION_REQUIRED, "E10003", "서버내 API 등록 정보가 없습니다.")
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
