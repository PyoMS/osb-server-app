package com.osb.osbserverapp.common.exception;

import com.osb.osbserverapp.common.ApiError;
import lombok.Getter;

@Getter
public abstract class ApiCommonException extends RuntimeException{
    private final ApiError apiError;

    public ApiCommonException(ApiError apiError) {
        super(apiError.getErrorMessage());
        this.apiError = apiError;
    }

    public ApiCommonException(ApiError apiError, String message) {
        super(message);
        this.apiError = apiError;
    }
}
