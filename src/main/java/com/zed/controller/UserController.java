package com.zed.controller;

import cn.hutool.core.util.StrUtil;
import com.zed.common.Constants;
import com.zed.common.Result;
import com.zed.controller.dto.UserDTO;
import com.zed.entity.User;
import com.zed.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

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

    @GetMapping("/username/{username}")
    public Result findUser(@PathVariable String username) {
        User user = userService.selectUserByUserName(username);
        System.out.println(user);
        return Result.success(user);
    }

    @PostMapping("/add")
    public Result createUser(@RequestBody User user) {
        return Result.success(userService.addUser(user));
    }

    @PostMapping("/update")
    public Result updateUser(@RequestBody User user) {
        return Result.success(userService.updateUser(user));
    }


    @DeleteMapping("/{id}")
    public Result deleteUser(@PathVariable Integer id) { //表示url参数
        return Result.success(userService.deleteUserById(id));
    }

    @PostMapping("/del/batch")
    public Result deleteUserBatch(@RequestBody List<Integer> ids) {
        return Result.success(userService.deleteUserBatchById(ids));
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
                                              @RequestParam(defaultValue = "") String nickname,
                                              @RequestParam(defaultValue = "") String phone,
                                              @RequestParam(defaultValue = "") String email) {
        pageNum = (pageNum - 1) * pageSize;
        List<User> data = userService.selectUserByPage(pageNum, pageSize, nickname, phone, email);
        Integer totalUser = userService.selectTotalUser(nickname, phone, email);
        Map<String, Object> res = new HashMap<>();
        res.put("data", data);
        res.put("totalUser", totalUser);
        return res;
    }

}
