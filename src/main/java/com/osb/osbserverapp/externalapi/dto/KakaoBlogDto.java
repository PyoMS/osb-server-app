package com.osb.osbserverapp.externalapi.dto;

import lombok.Data;

@Data
public class KakaoBlogDto {
    private String blogname;
    private String contents;
    private String datetime;
    private String thumbnail;
    private String title;
    private String url;
}
