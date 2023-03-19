package com.osb.osbserverapp.api;

import com.osb.osbserverapp.dto.ObsGetListReq;
import com.osb.osbserverapp.dto.ObsTopGetListRes;
import com.osb.osbserverapp.externalapi.dto.OsbBlogSearchResponse;
import com.osb.osbserverapp.service.ObsLogService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api")
public class ObsLogApi {

    private final ObsLogService obsLogService;

    @ApiOperation(value = "블로그 검색", notes = "블로그 검색 조회")
    @GetMapping("/search")
    public OsbBlogSearchResponse searchBlogList(@Validated ObsGetListReq obsGetListReq) {
        log.info("searchBlogList");
        return obsLogService.searchBlogList(obsGetListReq);
    }

    @ApiOperation(value = "인기 검색어 TOP 10", notes = "인기 검색어 TOP 10 리스트 조회")
    @GetMapping("/search/topten")
    public ObsTopGetListRes searchTopTen(){ // no parameter. 상위 10위만 조회.
        log.info("searchTopTen");
        return obsLogService.searchTopTen();
    }

}
