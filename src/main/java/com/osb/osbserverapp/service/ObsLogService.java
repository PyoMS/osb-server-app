package com.osb.osbserverapp.service;

import com.osb.osbserverapp.domain.ObsLog;
import com.osb.osbserverapp.dto.ObsGetListReq;
import com.osb.osbserverapp.dto.ObsGetListRes;
import com.osb.osbserverapp.repository.ObsLogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ObsLogService {
    private final ObsLogRepository obsLogRepository;

    @Transactional
    public ObsGetListRes searchBlogList(ObsGetListReq obsGetListReq){
        // TODO 1) 외부 API 조회 - 검색되는
        //  data > 0 이면, 해당 검색어 엔터티에 저장.
        //  0이면 조회된 결과 없음으로 반환할 것.


        if(obsLogRepository.findByText(obsGetListReq.getText()).isPresent()){
            ObsLog obsLog = obsLogRepository.findByText(obsGetListReq.getText()).get();
            obsLog.plusCount();
        }else{
            ObsLog obsLog = ObsLog.builder()
                    .text(obsGetListReq.getText())
                    .count(1L)
                    .build();
            obsLogRepository.save(obsLog);
        }
        return null;
    }

}
