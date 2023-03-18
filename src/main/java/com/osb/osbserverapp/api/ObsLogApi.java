package com.osb.osbserverapp.api;

import com.osb.osbserverapp.dto.ObsGetListReq;
import com.osb.osbserverapp.dto.ObsGetListRes;
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
    public ObsGetListRes searchBlogList(ObsGetListReq obsGetListReq) {
        log.info("searchBlogList");
        System.out.println("obsGetListReq = " + obsGetListReq);
//        TODO Service 단에서 외부 접근 가능하도록
        return null;
    }
}
