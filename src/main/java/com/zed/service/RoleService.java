package com.zed.service;


import com.zed.entity.Role;
import com.zed.entity.User;

import java.util.List;

public interface RoleService {

    int addRole(Role role);

    int updateRole(Role role);

    Integer deleteRoleById(Integer id);

    boolean deleteRoleBatchById(List<Integer> ids);

    List<Role> selectRoleByPage(Integer pageNum, Integer pageSize, String name);

    Integer selectTotalRole(String name);


}
