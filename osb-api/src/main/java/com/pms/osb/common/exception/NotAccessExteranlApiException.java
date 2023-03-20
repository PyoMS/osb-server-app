package com.pms.osb.common.exception;


import static com.pms.osb.common.ApiError.NOT_ACCESS_EXTERANL_API_EXCEPTION;

public class NotAccessExteranlApiException extends ApiCommonException {
    public NotAccessExteranlApiException() {
        super(NOT_ACCESS_EXTERANL_API_EXCEPTION);
    }

    public NotAccessExteranlApiException(String message) {
        super(NOT_ACCESS_EXTERANL_API_EXCEPTION, message);
    }

}
