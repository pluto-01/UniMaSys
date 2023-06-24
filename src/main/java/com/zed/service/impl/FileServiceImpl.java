package com.zed.service.impl;

import com.zed.entity.Files;
import com.zed.entity.User;
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

    @Override
    public int updateFileEnable(Files files) {
        return fileMapper.updateFileEnable(files);
    }

    @Override
    public List<Files> selectFileByPage(Integer pageNum, Integer pageSize, String name) {
        return fileMapper.selectFileByPage(pageNum, pageSize, name);
    }

    @Override
    public Integer selectTotalFile(String name) {
        return fileMapper.selectTotalFile(name);
    }

    @Override
    public Integer deleteFileById(Integer id) {
        return fileMapper.deleteFileById(id);
    }

    @Override
    public boolean deleteFileBatchById(List<Integer> ids) {
        return fileMapper.deleteFileBatchById(ids);
    }

}
