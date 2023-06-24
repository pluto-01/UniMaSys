package com.zed.mapper;


import com.zed.entity.Files;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface FileMapper {

    @Select("select * from sys_file where md5 = #{md5}")
    List<Files> selectMd5(@Param("md5") String md5);

    Integer addFile(@Param("name") String name, @Param("type") String type, @Param("size") Long size, @Param("url") String url, @Param("md5") String md5);
}
