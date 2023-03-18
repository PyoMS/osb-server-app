package com.osb.osbserverapp.api;

import com.osb.osbserverapp.dto.ObsGetListReq;
import com.osb.osbserverapp.dto.ObsTopGetListRes;
import com.osb.osbserverapp.externalapi.dto.KakaoBlogResponse;
import com.osb.osbserverapp.service.ObsLogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api")
public class ObsLogApi {

    private final ObsLogService obsLogService;

    @GetMapping("/search")
    public KakaoBlogResponse searchBlogList(ObsGetListReq obsGetListReq) {
        log.info("searchBlogList");
        return obsLogService.searchBlogList(obsGetListReq);
    }

    @GetMapping("/search/topten")
    public ObsTopGetListRes searchTopTen(){ // no parameter. 상위 10위만 조회.
        log.info("searchTopTen");
        return obsLogService.searchTopTen();
    }

}
