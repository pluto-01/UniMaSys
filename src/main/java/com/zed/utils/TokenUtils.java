package com.zed.utils;

import cn.hutool.core.date.DateUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Date;

/**
 * 生成Token
 */
public class TokenUtils {
    public static String getToken(String userId, String sign) {
        return JWT.create().withAudience(userId)   //将userId 保存到token里面，作为载荷
                .withExpiresAt(DateUtil.offsetHour(new Date(), 2))  //2小时后token过期
                .sign(Algorithm.HMAC256(sign + "ZedTokenSuffix")); //以password 作为 token 的密钥
    }
}
