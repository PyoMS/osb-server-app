package com.pms.osb.common.advice;

import com.pms.osb.common.ApiError;
import com.pms.osb.common.dto.DefaultRes;
import com.pms.osb.common.exception.NotAccessExteranlApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
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
        FieldError fieldError = ex.getBindingResult().getFieldError();
        String detailMessage = "";
        log.info("handleBindException : " + fieldError.getField());
        if(fieldError.getField().equals("text")){
            detailMessage = "검색어를 입력하여 주세요.";
        } else if(fieldError.getField().equals("sort")){
            detailMessage = String.format("%s 값은 %s 이거나 %s 중 하나를 입력하여 주십시오.", fieldError.getField()
                    , "accuracy(정확도순)"
                    , "recency(최신순)");
        } else {
            detailMessage = String.format("%s 값은 %s (입력값: %s)", fieldError.getField()
                    , fieldError.getDefaultMessage()
                    , fieldError.getRejectedValue());
        }
        DefaultRes defaultRes = DefaultRes.builder()
                .apiError(ApiError.HANDLE_BIND_EXCEPTION)
                .detailMessage(detailMessage)
                .moreInfo(ex.getMessage())
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
