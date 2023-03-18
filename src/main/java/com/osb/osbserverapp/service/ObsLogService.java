package com.osb.osbserverapp.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.osb.osbserverapp.common.exception.NotExistBlogSearchException;
import com.osb.osbserverapp.common.exception.NotInputTextException;
import com.osb.osbserverapp.domain.ObsLog;
import com.osb.osbserverapp.dto.ObsGetListReq;
import com.osb.osbserverapp.dto.ObsTopGetListRes;
import com.osb.osbserverapp.externalapi.dto.KakaoBlogResponse;
import com.osb.osbserverapp.repository.ObsLogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;


@Service
@RequiredArgsConstructor
@Slf4j
public class ObsLogService {
    private final ObsLogRepository obsLogRepository;
    private final RestTemplate restTemplate;

    @Value("${kakaoAPI}")
    private String apiKey;

    @Transactional
    public KakaoBlogResponse searchBlogList(ObsGetListReq obsGetListReq) {
        log.info("obsGetListReq - " + obsGetListReq);
        if(obsGetListReq.getText()==null){
            throw new NotInputTextException("검색어를 입력하여 주시기 바랍니다.");
        }
        KakaoBlogResponse kakaoBlogResponse = null;
        StringBuilder sb = new StringBuilder("KakaoAK ");
        ObjectMapper objectMapper = new ObjectMapper();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", sb.append(apiKey).toString());
        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);

        ResponseEntity<String> exchange = restTemplate.exchange("http://dapi.kakao.com/v2/search/blog?query=" + obsGetListReq.getText()
                + "&recency="+obsGetListReq.getSort().toString()+"&page="+obsGetListReq.getPage()+"&size="+obsGetListReq.getSize(),
                HttpMethod.GET, entity, String.class);
        try {
            kakaoBlogResponse = objectMapper.readValue(exchange.getBody(), KakaoBlogResponse.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        if(kakaoBlogResponse == null || kakaoBlogResponse.getMeta().getTotal_count()==0){
            throw new NotExistBlogSearchException();
        }else{
            if (obsLogRepository.findByText(obsGetListReq.getText()).isPresent()) { // 조회이력 존재할 경우
                ObsLog obsLog = obsLogRepository.findByText(obsGetListReq.getText()).get();
                obsLog.plusCount();
            } else { // 조회이력 없을 경우
                ObsLog obsLog = ObsLog.builder()
                        .text(obsGetListReq.getText())
                        .count(1L)
                        .build();
                obsLogRepository.save(obsLog);
            }
            return kakaoBlogResponse;
        }
    }

    public ObsTopGetListRes searchTopTen() {
        return new ObsTopGetListRes(obsLogRepository.findTopTenList());
    }

}
