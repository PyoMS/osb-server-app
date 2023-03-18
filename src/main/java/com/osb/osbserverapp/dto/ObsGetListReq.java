package com.osb.osbserverapp.dto;

import com.osb.osbserverapp.code.SearchSort;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ObsGetListReq {
    private String text; // 검색어
    private SearchSort sort;
    private int page;
    private int size;

    public ObsGetListReq(String text, SearchSort sort, int page, int size) {
        this.text = text;
        this.sort = sort;
        this.page = page;
        this.size = size;
    }
}
