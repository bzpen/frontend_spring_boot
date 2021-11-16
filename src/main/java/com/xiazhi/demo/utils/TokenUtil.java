package com.xiazhi.demo.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.xiazhi.demo.model.User;

import java.util.Date;

/**
 * @author   kaito kuroba
 * @Email   3118659412@qq.com
 * @since   2021/10/10
 */
public class TokenUtil {

    private static final long EXPIRE_TIME = 15 * 24 * 60 * 60 * 1000;
    private static final String TOKEN_SECRET = "txdy";  //密钥盐

    /**
     * 签名生成
     *
     * @param user
     * @return
     */
    public static String sign(User user) {
        String token = null;
        try {
            Date expiresAt = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            token = JWT.create()
                    .withIssuer("auth0")
                    .withClaim("username", user.getUserName())
                    .withExpiresAt(expiresAt)
                    // 使用了HMAC256加密算法。
                    .sign(Algorithm.HMAC256(TOKEN_SECRET));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return token;
    }

    /**
     * 签名验证
     *
     * @param token
     * @return
     */
    public static boolean verify(String token) {
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(TOKEN_SECRET)).withIssuer("auth0").build();
            DecodedJWT jwt = verifier.verify(token);
            System.out.println("认证通过：");
            System.out.println("username: " + jwt.getClaim("username").asString());
            System.out.println("过期时间：      " + jwt.getExpiresAt());
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    /**
     * 签名解读用户名
     *
     * @param token
     * @return
     */
    public static User tokenUser(String token) {
        try {
            User user=new User();
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(TOKEN_SECRET)).withIssuer("auth0").build();
            DecodedJWT jwt = verifier.verify(token);
            user.setUserName(jwt.getClaim("username").asString());
            return user;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}
