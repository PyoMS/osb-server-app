package com.osb.osbserverapp.externalapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class KakaoBlogResponse {
    private List<Documents> documents;
    private Meta meta;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Documents{
        String title;
        String contents;
        String url;
        String blogname;
        String thumbnail;
        String datetime;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Meta{
        Integer total_count;
        Integer pageable_count;
        Boolean is_end;
    }
}
