package com.pms.osb.api.service.osblog.dto;

import com.pms.osb.domain.osblog.entity.ObsLog;
import com.pms.osb.domain.osblog.vo.TopTenVo;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class ObsTopGetListRes {
    private List<TopTenVo> topTenVoList;

    public ObsTopGetListRes(List<ObsLog> obsTopList) {
        this.topTenVoList = obsTopList.stream().map(TopTenVo::new).collect(Collectors.toList());
    }
}
