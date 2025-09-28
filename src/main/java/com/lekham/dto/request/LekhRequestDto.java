package com.lekham.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LekhRequestDto {

    private String title;
    private String content;
    private String bgc = "white";
    private String txtc = "black";
    private String access;
    private String priority;
    private long category;
    private long parent;

}


