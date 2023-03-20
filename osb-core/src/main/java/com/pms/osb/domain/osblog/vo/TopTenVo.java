package com.pms.osb.domain.osblog.vo;

import com.pms.osb.domain.osblog.entity.ObsLog;
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
