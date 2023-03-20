package com.pms.osb.common.advice;

import com.pms.osb.common.ApiError;
import com.pms.osb.common.dto.DefaultRes;
import com.pms.osb.common.exception.NotAccessExteranlApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice
public class ApiCommonAdvice extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        DefaultRes defaultRes = DefaultRes.builder()
                .apiError(ApiError.VALIDATION_EXCEPTION)
                .detailMessage("Validation Failed")
                .moreInfo(ex.getBindingResult().toString())
                .build();
        return new ResponseEntity<>(defaultRes, ApiError.VALIDATION_EXCEPTION.getHttpStatus());
    }

    @Override
    protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        DefaultRes defaultRes = DefaultRes.builder()
                .apiError(ApiError.HANDLE_BIND_EXCEPTION)
                .detailMessage("HANDLE_BIND_EXCEPTION")
                .moreInfo(ex.getBindingResult().toString())
                .build();
        return new ResponseEntity<>(defaultRes, ApiError.HANDLE_BIND_EXCEPTION.getHttpStatus());
    }


    @ExceptionHandler({NotAccessExteranlApiException.class})
    public ResponseEntity<DefaultRes> handleNotAccessExteranlApiException(NotAccessExteranlApiException e){
        DefaultRes defaultRes = DefaultRes.builder()
                .apiError(ApiError.NOT_ACCESS_EXTERANL_API_EXCEPTION)
                .detailMessage(e.getMessage())
                .moreInfo("")
                .build();
        return new ResponseEntity<>(defaultRes, ApiError.NOT_ACCESS_EXTERANL_API_EXCEPTION.getHttpStatus());
    }
}
