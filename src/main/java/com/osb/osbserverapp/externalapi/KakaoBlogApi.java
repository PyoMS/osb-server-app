package com.osb.osbserverapp.externalapi;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.osb.osbserverapp.externalapi.dto.KakaoBlogDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RequiredArgsConstructor
public class KakaoBlogApi {
    private final RestTemplate restTemplate;

    public List<KakaoBlogDto> searchBlog(){

        return null;
    }
}
