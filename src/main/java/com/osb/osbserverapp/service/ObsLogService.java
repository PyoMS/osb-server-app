package com.osb.osbserverapp.service;

import com.osb.osbserverapp.code.SearchSort;
import com.osb.osbserverapp.common.exception.NotExistBlogSearchException;
import com.osb.osbserverapp.common.exception.NotInputTextException;
import com.osb.osbserverapp.domain.ObsLog;
import com.osb.osbserverapp.dto.ObsGetListReq;
import com.osb.osbserverapp.dto.ObsTopGetListRes;
import com.osb.osbserverapp.externalapi.KakaoBlogApi;
import com.osb.osbserverapp.externalapi.NaverBlogApi;
import com.osb.osbserverapp.externalapi.dto.OsbBlogSearchResponse;
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
    private final KakaoBlogApi kakaoBlogApi;
    private final NaverBlogApi naverBlogApi;


    @Transactional
    public OsbBlogSearchResponse searchBlogList(ObsGetListReq obsGetListReq) {
        if(obsGetListReq.getText()==null){
            throw new NotInputTextException("검색어를 입력하여 주시기 바랍니다.");
        }
        OsbBlogSearchResponse osbBlogSearchResponse = null;

        osbBlogSearchResponse = kakaoBlogApi.searchBlog(
                obsGetListReq.getText(),
                obsGetListReq.getSort().toString(),
                obsGetListReq.getPage(),
                obsGetListReq.getSize()
        );

        //TODO OsbBlogSearchResponse Transfer 메소드 or 클래스 추가
        Object o = naverBlogApi.searchBlog(obsGetListReq.getText(),
                transferSort(obsGetListReq.getSort()),
                obsGetListReq.getPage(),
                obsGetListReq.getSize());
        System.out.println("naverObject = " + o);


        if(osbBlogSearchResponse == null || osbBlogSearchResponse.getMeta().getTotal_count()==0){
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
            return osbBlogSearchResponse;
        }
    }
    private String transferSort(SearchSort searchSort){
        return searchSort == SearchSort.accuracy ? "sim" : "date";
    }

    public ObsTopGetListRes searchTopTen() {
        return new ObsTopGetListRes(obsLogRepository.findTopTenList());
    }

}
