package com.zed.service.impl;

import com.zed.entity.Files;
import com.zed.mapper.FileMapper;
import com.zed.service.FileService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class FileServiceImpl implements FileService {

    @Resource
    private FileMapper fileMapper;

    @Override
    public List<Files> selectMd5(String md5) {
        return fileMapper.selectMd5(md5);
    }

    @Override
    public Integer addFile(String name, String type, Long size, String url, String md5) {
        return fileMapper.addFile(name, type, size, url, md5);
    }
}
