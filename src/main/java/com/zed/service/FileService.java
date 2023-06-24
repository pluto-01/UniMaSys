package com.zed.service;

import com.zed.entity.Files;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FileService {


    List<Files> selectMd5(String md5);

    Integer addFile(String name, String type, Long size, String url, String md5);
}
