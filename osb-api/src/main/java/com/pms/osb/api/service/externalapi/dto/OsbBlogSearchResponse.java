package com.pms.osb.api.service.externalapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class OsbBlogSearchResponse {
    private Integer total_count;
    private List<Documents> documents;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Documents{
        String title; // naver: title
        String contents; // naver: description
        String url; // naver: link
        String blogname; // naver: bloggername
//        String thumbnail; // 제거 - 네이버와 동기화
        String datetime; // naver: postdate
    }

    public OsbBlogSearchResponse(KakaoSearchResponse kakaoSearchResponse) {
        this.total_count = kakaoSearchResponse.getMeta().getTotal_count();
        this.documents = kakaoSearchResponse.getDocuments().stream().map(item ->
                new Documents(item.getTitle(), item.getContents(), item.getUrl(), item.getBlogname(), item.getDatetime())
        ).collect(Collectors.toList());
    }


    public OsbBlogSearchResponse(NaverSearchResponse naverSearchResponse) {
        this.total_count = naverSearchResponse.getTotal();
        this.documents = naverSearchResponse.getItems().stream().map(item ->
                new Documents(item.getTitle(), item.getDescription(), item.getLink(), item.getBloggername(), item.getPostdate())
        ).collect(Collectors.toList());

    }
}
