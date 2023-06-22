package com.zed.service.impl;

import com.zed.common.Constants;
import com.zed.controller.dto.UserDTO;
import com.zed.entity.User;
import com.zed.exception.ServiceException;
import com.zed.mapper.UserMapper;
import com.zed.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.rmi.ServerException;


@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public int save(User user) {
        if (user.getId() == null) {  //User没id则为新增操作
            return userMapper.addUser(user);
        } else {  //否则为更新
            return userMapper.updateUser(user);
        }

    }

//    @Override
//    public Integer deleteUserById(Integer id) {
//        return userMapper.deleteUserById(id);
//    }
//
//    @Override
//    public boolean deleteUserBatchById(List<Integer> ids) {
//        return userMapper.deleteUserBatchById(ids);
//    }

    @Override
    public UserDTO login(UserDTO userDTO) {
        User user = userMapper.selectUserByNameAndPWD(userDTO.getUsername(), userDTO.getPassword());
        try {
            if (user != null) {
                BeanUtils.copyProperties(user, userDTO);
                return userDTO;
            } else {
                throw new Exception("用户名或密码错误");
            }
        } catch (Exception e) {
            throw new ServiceException(Constants.CODE_600, "用户名或密码错误");
        }
    }


}
