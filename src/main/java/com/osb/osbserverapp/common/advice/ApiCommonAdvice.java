package com.osb.osbserverapp.common.advice;

import com.osb.osbserverapp.common.ApiError;
import com.osb.osbserverapp.common.dto.DefaultRes;
import com.osb.osbserverapp.common.exception.NotExistBlogSearchException;
import com.osb.osbserverapp.common.exception.NotInputTextException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ApiCommonAdvice {

    @ExceptionHandler({NotInputTextException.class})
    public ResponseEntity<DefaultRes> handleNotInputTextException(NotInputTextException e){
        DefaultRes defaultRes = DefaultRes.builder()
                .apiError(ApiError.NOT_INPUT_TEXT)
                .detailMessage("")
                .moreInfo("")
                .build();
        return new ResponseEntity<>(defaultRes, ApiError.NOT_INPUT_TEXT.getHttpStatus());
    }

    @ExceptionHandler({NotExistBlogSearchException.class})
    public ResponseEntity<DefaultRes> handleNotExistBlogSearchException(NotExistBlogSearchException e){
        DefaultRes defaultRes = DefaultRes.builder()
                .apiError(ApiError.NOT_EXIST_BLOG_SEARCH)
                .detailMessage("")
                .moreInfo("")
                .build();
        return new ResponseEntity<>(defaultRes, ApiError.NOT_EXIST_BLOG_SEARCH.getHttpStatus());
    }
}
