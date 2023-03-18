package com.osb.osbserverapp.service;

import com.osb.osbserverapp.domain.ObsLog;
import com.osb.osbserverapp.dto.ObsGetListReq;
import com.osb.osbserverapp.dto.ObsGetListRes;
import com.osb.osbserverapp.dto.ObsTopGetListRes;
import com.osb.osbserverapp.repository.ObsLogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Slf4j
public class ObsLogService {
    private final ObsLogRepository obsLogRepository;

    @Transactional
    public ObsGetListRes searchBlogList(ObsGetListReq obsGetListReq){
        // TODO 추후 kakao api health 체크 - 응답없을 시 naver로 전환
        // TODO kakao api 검색 결과값 0 일경우 null 처리


        if(obsLogRepository.findByText(obsGetListReq.getText()).isPresent()){ // 조회이력 존재할 경우
            ObsLog obsLog = obsLogRepository.findByText(obsGetListReq.getText()).get();
            obsLog.plusCount();
        } else{ // 조회이력 없을 경우
            ObsLog obsLog = ObsLog.builder()
                    .text(obsGetListReq.getText())
                    .count(1L)
                    .build();
            obsLogRepository.save(obsLog);
        }
        return null;
    }

    public ObsTopGetListRes searchTopTen(){
        return new ObsTopGetListRes(obsLogRepository.findTopTenList());
    }

}
