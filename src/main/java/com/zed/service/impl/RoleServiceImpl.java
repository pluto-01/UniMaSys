package com.zed.service.impl;

import com.zed.common.Constants;
import com.zed.entity.Role;
import com.zed.entity.User;
import com.zed.exception.ServiceException;
import com.zed.mapper.RoleMapper;
import com.zed.service.RoleService;
import com.zed.utils.TokenUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class RoleServiceImpl implements RoleService {
    @Resource
    private RoleMapper roleMapper;


    @Override
    public int addRole(Role role) {
        return roleMapper.addRole(role);
    }

    @Override
    public int updateRole(Role role) {
        return roleMapper.updateRole(role);
    }

    @Override
    public Integer deleteRoleById(Integer id) {
        return roleMapper.deleteRoleById(id);
    }

    @Override
    public boolean deleteRoleBatchById(List<Integer> ids) {
        return roleMapper.deleteRoleBatchById(ids);
    }


    @Override
    public List<Role> selectRoleByPage(Integer pageNum, Integer pageSize, String name) {
        return roleMapper.selectRoleByPage(pageNum, pageSize, name);
    }

    @Override
    public Integer selectTotalRole(String name) {
        return roleMapper.selectTotalRole(name);
    }


}
