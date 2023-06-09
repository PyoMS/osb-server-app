package com.pms.osb.api.service.osblog;

import com.pms.osb.api.service.externalapi.KakaoBlogApi;
import com.pms.osb.api.service.externalapi.NaverBlogApi;
import com.pms.osb.api.service.externalapi.dto.OsbBlogSearchResponse;
import com.pms.osb.api.service.osblog.code.SearchSort;
import com.pms.osb.api.service.osblog.dto.ObsGetListReq;
import com.pms.osb.api.service.osblog.dto.ObsTopGetListRes;
import com.pms.osb.common.exception.NotAccessExteranlApiException;
import com.pms.osb.domain.osblog.entity.ObsLog;
import com.pms.osb.domain.osblog.repository.ObsLogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;

import static com.pms.osb.common.ApiError.UNAUTHORIZED;


@Service
@RequiredArgsConstructor
@Slf4j
public class ObsLogService {
    private final ObsLogRepository obsLogRepository;
    private final KakaoBlogApi kakaoBlogApi;
    private final NaverBlogApi naverBlogApi;


    @Transactional
    public OsbBlogSearchResponse searchBlogList(ObsGetListReq obsGetListReq) {
        OsbBlogSearchResponse osbBlogSearchResponse = null;
        try {
            osbBlogSearchResponse = new OsbBlogSearchResponse(kakaoBlogApi.searchBlog(obsGetListReq.getText(), obsGetListReq.getSort().toString(), obsGetListReq.getPage(), obsGetListReq.getSize()));
        } catch (HttpClientErrorException e){
            if(e.getStatusCode().is4xxClientError()){
                log.error("Please Kakao API Check! : " + UNAUTHORIZED);
            }
            if(e.getStatusCode().is5xxServerError()){ // 카카오 API 5xx 에러 시 Naver API로 처리
                log.info("Process Naver API Blog Search");
                osbBlogSearchResponse = new OsbBlogSearchResponse(naverBlogApi.searchBlog(obsGetListReq.getText(), transferSort(obsGetListReq.getSort()), obsGetListReq.getPage(), obsGetListReq.getSize()));
            }
            else {
                log.error(e.getMessage());
                throw new NotAccessExteranlApiException();
            }
        }

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
    private String transferSort(SearchSort searchSort){
        return searchSort == SearchSort.accuracy ? "sim" : "date";
    }

    public ObsTopGetListRes searchTopTen() {
        return new ObsTopGetListRes(obsLogRepository.findTopTenList());
    }

}
