package com.pms.osb.api.service.externalapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pms.osb.api.service.externalapi.dto.OsbBlogSearchResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;


@Configuration
@RequiredArgsConstructor
public class KakaoBlogApi {
    private final RestTemplate restTemplate;

    @Value("${kakaoAPI}")
    private String apiKey;

    public OsbBlogSearchResponse searchBlog(String query, String sort, Integer page, Integer size){
        StringBuilder sb = new StringBuilder("KakaoAK ");
        ObjectMapper objectMapper = new ObjectMapper();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", sb.append(apiKey, 0, apiKey.length()-2).toString());
        HttpEntity<String> entity = new HttpEntity<>("", httpHeaders);
        ResponseEntity<String> exchange = restTemplate.exchange("http://dapi.kakao.com/v2/search/blog" +
                "?query=" + query
                + "&recency=" + sort
                + "&page=" + page
                + "&size=" + size,
                HttpMethod.GET, entity, String.class);
        try {
            return objectMapper.readValue(exchange.getBody(), OsbBlogSearchResponse.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
