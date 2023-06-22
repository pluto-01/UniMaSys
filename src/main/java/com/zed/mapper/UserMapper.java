package com.zed.mapper;

import org.apache.ibatis.annotations.*;
import com.zed.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UserMapper {

    @Select("select * from sys_user where status = 0")
    List<User> selectAllUser();

    @Insert("insert into sys_user(username, password, gender,phone,email,address) " +
            "values (#{username},#{password},#{gender},#{phone},#{email},#{address})")
    int addUser(User user);

    int updateUser(User user);

    @Update("update sys_user set status = 1 where id = #{id} ")
        //  假删除
    Integer deleteUserById(@Param("id") Integer id);

    boolean deleteUserBatchById(List<Integer> ids);

    @Select("select * from sys_user where(username like concat('%',#{username},'%') and phone like concat('%',#{phone},'%') and email like concat('%',#{email},'%') and status != 1) limit #{pageNum},#{pageSize}")
    List<User> selectUserByPage(@Param("pageNum") Integer pageNum,
                                @Param("pageSize") Integer pageSize,
                                @Param("username") String username,
                                @Param("phone") String phone,
                                @Param("email") String email);

    @Select("select count(*) from sys_user where(username like concat('%',#{username},'%') " +
            "and phone like concat('%',#{phone},'%') and email like concat('%',#{email},'%') and status != 1) ")
    Integer selectTotalUser(@Param("username") String username,
                            @Param("phone") String phone,
                            @Param("email") String email);

    @Select("select username,password from sys_user where username = #{username} and password = #{password}")
    User selectUserByNameAndPWD(@Param("username") String username, @Param("password") String password);
}
