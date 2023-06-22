package com.zed.service;


import com.zed.controller.dto.UserDTO;
import com.zed.entity.User;
import org.springframework.stereotype.Service;

//@Service  //把类注入到Spring容器
public interface UserService {
    int save(User user);

//    Integer deleteUserById(Integer id);
//
//    boolean deleteUserBatchById(List<Integer> ids);

    UserDTO login(UserDTO userDTO);

//    List<User> selectUserByPage(Integer pageNum, Integer pageSize, String username, String phone, String email);
}
