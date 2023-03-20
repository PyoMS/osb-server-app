package com.pms.osb.common.exception;


import static com.pms.osb.common.ApiError.NOT_EXIST_BLOG_SEARCH;

public class NotExistBlogSearchException extends ApiCommonException {
    public NotExistBlogSearchException() {
        super(NOT_EXIST_BLOG_SEARCH);
    }

    public NotExistBlogSearchException(String message) {
        super(NOT_EXIST_BLOG_SEARCH, message);
    }
}
