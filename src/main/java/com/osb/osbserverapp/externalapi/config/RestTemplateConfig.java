package com.osb.osbserverapp.externalapi.config;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;



@Configuration
public class RestTemplateConfig { // RestTemplate Config
    @Bean
    @LoadBalanced
    public RestTemplate getCustomRestTemplate() {
        HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        httpRequestFactory.setConnectionRequestTimeout(2000); // 연결요청초과시간 2sec
        httpRequestFactory.setReadTimeout(10000); // 읽기시간초과 10sec
        HttpClient httpClient = HttpClientBuilder.create()
                .setMaxConnTotal(200) // 연결을 유지할 최대 숫자
                .setMaxConnPerRoute(20) // 특정 경로당 최대 숫자
                .build();
        httpRequestFactory.setHttpClient(httpClient);
        return new RestTemplate(httpRequestFactory);
    }

}
