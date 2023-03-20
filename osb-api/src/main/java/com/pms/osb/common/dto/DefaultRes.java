package com.pms.osb.common.dto;

import com.pms.osb.common.ApiError;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

@Slf4j
@Data
public class DefaultRes {
    private String httpStatus;
    private String errorCode;
    private String errorMessage;
    private String detailMessage;
    private String moreInfo;

    @Builder
    public DefaultRes(ApiError apiError, String detailMessage, String moreInfo) {
        HttpStatus httpStatus = apiError.getHttpStatus();
        this.httpStatus = String.valueOf(httpStatus.value());
        this.errorCode = apiError.getErrorCode();
        this.errorMessage = apiError.getErrorMessage();
        this.detailMessage = detailMessage;
        this.moreInfo = moreInfo;
        if(httpStatus.is5xxServerError()){
            log.error("httpStatus[{}] errorCode[{}], errorMessage[{}], detailMessage[{}]", this.httpStatus, this.errorCode, this.errorMessage, this.detailMessage);
        } else if (apiError != ApiError.COMMON_SUCCESS){
            log.info("httpStatus[{}] errorCode[{}], errorMessage[{}], detailMessage[{}]", this.httpStatus, this.errorCode, this.errorMessage, this.detailMessage);
        }
    }
}
