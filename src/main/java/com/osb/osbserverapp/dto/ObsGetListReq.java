package com.osb.osbserverapp.dto;

import com.osb.osbserverapp.code.SearchSort;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class ObsGetListReq {
    @NotNull(message = "검색어를 입력해주세요.")
    private String text; // 검색어
    private SearchSort sort = SearchSort.accuracy;
    private int page = 1;
    private int size = 10;

    public ObsGetListReq(String text, SearchSort sort, int page, int size) {
        this.text = text;
        this.sort = sort;
        this.page = page;
        this.size = size;
    }
}
