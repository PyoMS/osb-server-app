package com.pms.osb.externalapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;


@SpringBootTest
class NaverBlogApiTest {

    @Autowired
    RestTemplate restTemplate;
    @Value("${naverapi-id}")
    private String apiId;

    @Value("${naverapi-pw}")
    private String apiPw;

    @Test
    void searchBlog() {
        String query = "테스트";
        String sort = "sim"; // or date
        Integer page = 1; // max 100
        Integer size = 10; // max 100

        ObjectMapper objectMapper = new ObjectMapper();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("X-Naver-Client-Id", apiId);
        httpHeaders.add("X-Naver-Client-Secret", apiPw);
        HttpEntity<String> entity = new HttpEntity<>("", httpHeaders);
        ResponseEntity<String> exchange = restTemplate.exchange("https://openapi.naver.com/v1/search/blog.json" +
                "?query=" + query
                + "&sort=" + sort
                + "&start=" + page
                + "&display=" + size,
                HttpMethod.GET, entity, String.class);
        try {
            Object o = objectMapper.readValue(exchange.getBody(), Object.class);
            System.out.println("o = " + o);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}