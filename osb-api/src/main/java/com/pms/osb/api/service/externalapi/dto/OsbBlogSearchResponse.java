package com.pms.osb.api.service.externalapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class OsbBlogSearchResponse {
    private List<Documents> documents;
    private Meta meta;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Documents{
        String title; // naver: title
        String contents; // naver: description
        String url; // naver: link
        String blogname; // naver: bloggername
        String thumbnail; // FIXME 제거 - 네이버와 동기화
        String datetime; // naver: postdate
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Meta{
        Integer total_count;
        Integer pageable_count; // FIXME 제거 - 네이버와 동기화
        Boolean is_end; // FIXME 제거 - 네이버와 동기화
    }
}
