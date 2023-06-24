package com.zed.entity;


import lombok.Data;

@Data
public class Files {

    private Integer id;
    private String name;
    private String type;
    private Long size;
    private String url;
    private String md5;
    private Integer status;
    private Integer enable;

}
