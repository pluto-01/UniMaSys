package com.zed.controller;

import cn.hutool.core.util.StrUtil;
import com.zed.common.Constants;
import com.zed.common.Result;
import com.zed.controller.dto.UserDTO;
import com.zed.entity.User;
import com.zed.mapper.UserMapper;
import com.zed.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @PostMapping("/login")
    public Result login(@RequestBody UserDTO userDTO) {
        String username = userDTO.getUsername();
        String password = userDTO.getPassword();
        if (StrUtil.isBlank(username) || StrUtil.isBlank(password)) {
            return Result.error(Constants.CODE_400, "参数错误");
        }
        UserDTO dto = userService.login(userDTO);
        return Result.success(dto);

    }

    @PostMapping
    public Integer saveUser(@RequestBody User user) {
        return userService.save(user);
    }

    @DeleteMapping("/{id}")
    public Integer deleteUser(@PathVariable Integer id) { //表示url参数
        return userMapper.deleteUserById(id);
    }

    @PostMapping("/del/batch")
    public boolean deleteUserBatch(@RequestBody List<Integer> ids) {
        return userMapper.deleteUserBatchById(ids);
    }

    /**
     * 分页查询
     *
     * @return
     * @RequestParam接收分页参数 例如?pageNum=1&pageSize=10
     */
    @GetMapping("/page")
    public Map<String, Object> findUserByPage(@RequestParam Integer pageNum,
                                              @RequestParam Integer pageSize,
                                              @RequestParam(defaultValue = "") String username,
                                              @RequestParam(defaultValue = "") String phone,
                                              @RequestParam(defaultValue = "") String email) {
        pageNum = (pageNum - 1) * pageSize;
        List<User> data = userMapper.selectUserByPage(pageNum, pageSize, username, phone, email);
        Integer totalUser = userMapper.selectTotalUser(username, phone, email);
        Map<String, Object> res = new HashMap<>();
        res.put("data", data);
        res.put("totalUser", totalUser);
        return res;
    }

}
