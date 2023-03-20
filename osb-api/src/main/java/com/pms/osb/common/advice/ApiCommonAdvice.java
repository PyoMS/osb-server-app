package com.pms.osb.common.advice;

import com.pms.osb.common.ApiError;
import com.pms.osb.common.dto.DefaultRes;
import com.pms.osb.common.exception.NotExistBlogSearchException;
import com.pms.osb.common.exception.NotInputTextException;
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
