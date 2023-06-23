package com.zed.controller.dto;


import lombok.Data;

/**
 * 接收前端登录传来的参数
 */
@Data
public class UserDTO {
    private String username;
    private String password;
    private String nickname;
    private String avatar;
    private String token;

}
