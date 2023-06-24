package com.zed.mapper;


import com.zed.entity.Files;
import com.zed.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface FileMapper {

    @Select("select * from sys_file where md5 = #{md5}")
    List<Files> selectMd5(@Param("md5") String md5);

    Integer addFile(@Param("name") String name, @Param("type") String type, @Param("size") Long size, @Param("url") String url, @Param("md5") String md5);

    @Update("update sys_file set enable = #{enable} where id = #{id}")
    int updateFileEnable(Files files);

    @Select("select * from sys_file where(name like concat('%',#{name},'%')  and status = 0) limit #{pageNum},#{pageSize}")
    List<Files> selectFileByPage(@Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize, @Param("name") String name);

    @Select("select count(*) from sys_file where(name like concat('%',#{name},'%')  and status = 0) ")
    Integer selectTotalFile(@Param("name") String name);

    @Update("update sys_file set status = 1 where id = #{id} ")
    Integer deleteFileById(@Param("id") Integer id);

    boolean deleteFileBatchById(List<Integer> ids);
}
