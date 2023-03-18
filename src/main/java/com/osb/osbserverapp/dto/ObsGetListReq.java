package com.osb.osbserverapp.dto;

import com.osb.osbserverapp.code.SearchSort;
import lombok.Data;

@Data
public class ObsGetListReq {
    private String text; // 검색어
    private SearchSort sort;
    private int page;
    private int size;
}
