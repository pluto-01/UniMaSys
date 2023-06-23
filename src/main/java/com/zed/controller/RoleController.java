package com.zed.controller;

import cn.hutool.core.util.StrUtil;
import com.zed.common.Constants;
import com.zed.common.Result;
import com.zed.entity.Role;
import com.zed.service.RoleService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/role")
public class RoleController {
    @Resource
    private RoleService roleService;


    @PostMapping("/add")
    public Result createRole(@RequestBody Role role) {
        return Result.success(roleService.addRole(role));
    }

    @PostMapping("/update")
    public Result updateRole(@RequestBody Role role) {
        return Result.success(roleService.updateRole(role));
    }


    @DeleteMapping("/{id}")
    public Result deleteRole(@PathVariable Integer id) { //表示url参数
        return Result.success(roleService.deleteRoleById(id));
    }

    @PostMapping("/del/batch")
    public Result deleteRoleBatch(@RequestBody List<Integer> ids) {
        return Result.success(roleService.deleteRoleBatchById(ids));
    }

    /**
     * 分页查询
     *
     * @return
     * @RequestParam接收分页参数 例如?pageNum=1&pageSize=10
     */
    @GetMapping("/page")
    public Map<String, Object> findRoleByPage(@RequestParam Integer pageNum,
                                              @RequestParam Integer pageSize,
                                              @RequestParam(defaultValue = "") String name) {
        pageNum = (pageNum - 1) * pageSize;
        List<Role> data = roleService.selectRoleByPage(pageNum, pageSize, name);
        Integer totalRole = roleService.selectTotalRole(name);
        Map<String, Object> res = new HashMap<>();
        res.put("data", data);
        res.put("totalRole", totalRole);
        return res;
    }

}
