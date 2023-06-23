package com.zed.mapper;

import org.apache.ibatis.annotations.*;
import com.zed.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UserMapper {

    @Select("select * from sys_user where id = #{id} and status = 0")
    User selectUserInfo(Integer id);

    int addUser(User user);

    int updateUser(User user);

    @Update("update sys_user set status = 1 where id = #{id} ")
        //  假删除
    Integer deleteUserById(@Param("id") Integer id);

    boolean deleteUserBatchById(List<Integer> ids);

    @Select("select * from sys_user where(nickname like concat('%',#{nickname},'%') and phone like concat('%',#{phone},'%') and email like concat('%',#{email},'%') and status != 1) limit #{pageNum},#{pageSize}")
    List<User> selectUserByPage(@Param("pageNum") Integer pageNum,
                                @Param("pageSize") Integer pageSize,
                                @Param("nickname") String nickname,
                                @Param("phone") String phone,
                                @Param("email") String email);

    @Select("select count(*) from sys_user where(nickname like concat('%',#{nickname},'%') " +
            "and phone like concat('%',#{phone},'%') and email like concat('%',#{email},'%') and status != 1) ")
    Integer selectTotalUser(@Param("nickname") String nickname,
                            @Param("phone") String phone,
                            @Param("email") String email);

    @Select("select id,username,nickname,avatar from sys_user where username = #{username} and password = #{password}")
    User selectUserByNameAndPWD(@Param("username") String username, @Param("password") String password);

    @Select("select nickname,gender,phone,email,address from sys_user where username = #{username}")
    User selectUserByUserName(@Param("username") String username);
}
