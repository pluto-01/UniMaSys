package com.zed.mapper;


import com.zed.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface RoleMapper {
//    @Select("select * from sys_role where id = #{id} and flag = 0")
//    Role selectRoleInfo(Integer id);

    int addRole(Role role);

    int updateRole(Role role);

    @Update("update sys_role set flag = 1 where id = #{id} ")
        //  假删除
    Integer deleteRoleById(@Param("id") Integer id);

    boolean deleteRoleBatchById(List<Integer> ids);

    @Select("select * from sys_role where(name like concat('%',#{name},'%')  and flag != 1) limit #{pageNum},#{pageSize}")
    List<Role> selectRoleByPage(@Param("pageNum") Integer pageNum,
                                @Param("pageSize") Integer pageSize,
                                @Param("name") String name);

    @Select("select count(*) from sys_role where(name like concat('%',#{name},'%')  and flag != 1) ")
    Integer selectTotalRole(@Param("name") String name);
//
//    @Select("select id,name,avatar from sys_role where rolename = #{rolename} and password = #{password}")
//    Role selectRoleByNameAndPWD(@Param("rolename") String rolename, @Param("password") String password);

//    @Select("select name,gender,description,email,address from sys_role where rolename = #{rolename}")
//    Role selectRoleByRoleName(@Param("rolename") String rolename);
}
