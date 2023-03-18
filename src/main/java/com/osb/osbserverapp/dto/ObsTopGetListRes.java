package com.osb.osbserverapp.dto;

import com.osb.osbserverapp.domain.ObsLog;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class ObsTopGetListRes {
    private List<ObsLog> obsTopList;

    public ObsTopGetListRes(List<ObsLog> obsTopList) {
        this.obsTopList = obsTopList;
    }
}
