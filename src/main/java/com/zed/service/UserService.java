package com.zed.service;


import com.zed.controller.dto.UserDTO;
import com.zed.entity.User;

import java.util.List;

public interface UserService {

    User selectUserInfo(String id);

    int addUser(User user);

    int updateUser(User user);

    Integer deleteUserById(Integer id);

    //
    boolean deleteUserBatchById(List<Integer> ids);

    UserDTO login(UserDTO userDTO);

    List<User> selectUserByPage(Integer pageNum, Integer pageSize, String nickname, String phone, String email);

    Integer selectTotalUser(String nickname, String phone, String email);

    User selectUserByUserName(String username);
}
