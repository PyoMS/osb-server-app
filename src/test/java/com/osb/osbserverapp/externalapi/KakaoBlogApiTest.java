package com.osb.osbserverapp.externalapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.osb.osbserverapp.externalapi.dto.OsbBlogSearchResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;


@SpringBootTest
class KakaoBlogApiTest {
    @Autowired
    RestTemplate restTemplate;

    @Value("${kakaoAPI}")
    private String apiKey;

    @Test
    void searchBlog() {
        String query = "테스트";
        String sort = "accuracy";
        Integer page = 1;
        Integer size = 10;
        StringBuilder sb = new StringBuilder("KakaoAK ");
        ObjectMapper objectMapper = new ObjectMapper();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", sb.append(apiKey).toString());
        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);

        String url = "http://dapi.kakao.com/v2/search/blog";
        String urlTemplate = UriComponentsBuilder.fromUriString(url)
                .queryParam("query", query)
                .queryParam("recency", sort)
                .queryParam("page", page)
                .queryParam("size", size)
                .encode()
                .toUriString();
        System.out.println("urlTemplate = " + urlTemplate);

        ResponseEntity<String> exchange = restTemplate.exchange(urlTemplate,
                HttpMethod.GET,
                entity,
                String.class);
/*
        ResponseEntity<String> exchange = restTemplate.exchange("http://dapi.kakao.com/v2/search/blog?query=" + query
                + "&recency="+sort+"&page="+page+"&size="+size,
                HttpMethod.GET,
                entity,
                String.class);
        */
        try {
            OsbBlogSearchResponse osbBlogSearchResponse = objectMapper.readValue(exchange.getBody(), OsbBlogSearchResponse.class);
            System.out.println("osbBlogSearchResponse = " + osbBlogSearchResponse);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void UriComponentsBuilderTest(){
        String query = "테스트";
        String sort = "accuracy";
        Integer page = 1;
        Integer size = 10;
        String url = "http://dapi.kakao.com/v2/search/blog";

        UriComponents build = UriComponentsBuilder.fromUriString(url)
                .queryParam("query", query)
                .queryParam("recency", sort)
                .queryParam("page", page)
                .queryParam("size", size)
                .encode()
                .build();
        System.out.println("urlTemplate = " + build.toUriString());
    }
}