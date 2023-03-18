package com.osb.osbserverapp.dto;

import com.osb.osbserverapp.domain.ObsLog;
import com.osb.osbserverapp.vo.TopTenVo;
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
