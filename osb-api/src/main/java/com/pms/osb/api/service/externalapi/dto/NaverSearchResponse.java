package com.pms.osb.api.service.externalapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class NaverSearchResponse {
    String lastBuildDate;
    Integer total;
    Integer start;
    Integer display;
    private List<Items> items;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Items {
        String title;
        String link;
        String description;
        String bloggername;
        String bloggerlink;
        String postdate;
    }
}
