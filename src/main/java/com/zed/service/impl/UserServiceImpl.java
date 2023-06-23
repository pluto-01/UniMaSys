package com.zed.service.impl;

import com.zed.common.Constants;
import com.zed.controller.dto.UserDTO;
import com.zed.entity.User;
import com.zed.exception.ServiceException;
import com.zed.mapper.UserMapper;
import com.zed.service.UserService;
import com.zed.utils.TokenUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public User selectUserInfo(String id) {
        Integer userId = Integer.parseInt(id);
        return userMapper.selectUserInfo(userId);
    }

    @Override
    public int save(User user) {
        if (user.getId() == null && user.getUsername() == null) {  //User没id则为新增操作
            return userMapper.addUser(user);
        } else {  //否则为更新
            return userMapper.updateUser(user);
        }

    }

    @Override
    public Integer deleteUserById(Integer id) {
        return userMapper.deleteUserById(id);
    }

    @Override
    public boolean deleteUserBatchById(List<Integer> ids) {
        return userMapper.deleteUserBatchById(ids);
    }

    @Override
    public UserDTO login(UserDTO userDTO) {
        User user = userMapper.selectUserByNameAndPWD(userDTO.getUsername(), userDTO.getPassword());
        if (user != null) {
            BeanUtils.copyProperties(user, userDTO);
            //设置token
            String token = TokenUtils.getToken(user.getId().toString(), user.getPassword());
            System.out.println(token + user.getPassword());
            userDTO.setToken(token);
            return userDTO;
        } else {
            throw new ServiceException(Constants.CODE_600, "用户名或密码错误");
        }
    }

    @Override
    public List<User> selectUserByPage(Integer pageNum, Integer pageSize, String nickname, String phone, String email) {
        return userMapper.selectUserByPage(pageNum, pageSize, nickname, phone, email);
    }

    @Override
    public Integer selectTotalUser(String nickname, String phone, String email) {
        return userMapper.selectTotalUser(nickname, phone, email);
    }

    @Override
    public User selectUserByUserName(String username) {
        return userMapper.selectUserByUserName(username);
    }


}
