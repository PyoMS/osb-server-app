package com.osb.osbserverapp.common.exception;


import static com.osb.osbserverapp.common.ApiError.NOT_INPUT_TEXT;

public class NotInputTextException extends ApiCommonException {
    public NotInputTextException() {
        super(NOT_INPUT_TEXT);
    }

    public NotInputTextException(String message) {
        super(NOT_INPUT_TEXT, message);
    }
}