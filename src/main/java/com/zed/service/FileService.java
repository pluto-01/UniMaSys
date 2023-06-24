package com.zed.service;

import com.zed.common.Result;
import com.zed.entity.Files;
import com.zed.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface FileService {


    List<Files> selectMd5(String md5);

    Integer addFile(String name, String type, Long size, String url, String md5);

    int updateFileEnable(Files files);

    List<Files> selectFileByPage(Integer pageNum, Integer pageSize, String name);

    Integer selectTotalFile(String name);

    Integer deleteFileById(Integer id);

    boolean deleteFileBatchById(List<Integer> ids);
}
