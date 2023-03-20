package com.pms.osb.api.service.osblog.dto;

import com.pms.osb.api.service.osblog.code.SearchSort;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ObsGetListReq {
    @NotNull(message = "검색어를 입력해주세요.")
    @ApiModelProperty(value = "검색어 질의어")
    private String text; // 검색어

    @Enumerated(EnumType.STRING)
    @ApiModelProperty(value = "결과 문서 정렬 방식", notes = "accuracy(정확도순) 또는 recency(최신순)")
    private SearchSort sort = SearchSort.accuracy;

    @Max(50)
    @Min(1)
    @ApiModelProperty(value = "페이지", notes = "결과 페이지 번호")
    private int page = 1;
    @Max(50)
    @Min(1)
    @ApiModelProperty(value = "사이즈", notes = "한 페이지에 보여질 문서 수")
    private int size = 10;

}
