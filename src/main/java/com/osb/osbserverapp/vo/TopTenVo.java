package com.osb.osbserverapp.vo;

import com.osb.osbserverapp.domain.ObsLog;
import lombok.Data;

@Data
public class TopTenVo {
    private String text;
    private Long count;

    public TopTenVo(ObsLog obsLog) {
        this.text = obsLog.getText();
        this.count = obsLog.getCount();
    }
}
