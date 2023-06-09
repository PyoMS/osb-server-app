package com.pms.osb.api.service.externalapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pms.osb.api.service.externalapi.dto.NaverSearchResponse;
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
public class NaverBlogApi {
    private final RestTemplate restTemplate;
    @Value("${naverapi-id}")
    private String naverapiid;

    @Value("${naverapi-pw}")
    private String naverapipw;

    public NaverSearchResponse searchBlog(String query, String sort, Integer page, Integer size){
        ObjectMapper objectMapper = new ObjectMapper();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("X-Naver-Client-Id", naverapiid.substring(0,naverapiid.length()-2));
        httpHeaders.add("X-Naver-Client-Secret", naverapipw.substring(0, naverapipw.length()-2));
        HttpEntity<String> entity = new HttpEntity<>("", httpHeaders);
        ResponseEntity<String> exchange = restTemplate.exchange("https://openapi.naver.com/v1/search/blog.json" +
                "?query=" + query
                + "&sort=" + sort
                + "&start=" + page
                + "&display=" + size
                , HttpMethod.GET, entity, String.class);
        try {
            return objectMapper.readValue(exchange.getBody(), NaverSearchResponse.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}


