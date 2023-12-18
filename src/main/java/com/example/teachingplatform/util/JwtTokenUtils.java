package com.example.teachingplatform.util;



import com.example.teachingplatform.exception.PermissionException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * @author hehaoyang
 * jwt 工具类
 */
public class JwtTokenUtils {

    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";

    private static final String SECRET = "jwtsecret";
    private static final String ISS = "echisan";

    private static final String USER_ID_CLAIMS = "userId";

    private static final String USER_ROLE_CLAIMS = "userRole";



    /**
     * 选择了记住我之后的过期时间为7天
     */
    private static final long EXPIRATION = 60 * 60 * 24 * 7;
//    private static final long EXPIRATION = 1;

    /**
     * 创建token
     * @param username 用户名
     * @param userId 用户id
     * @return token字符串
     */
    public static String createToken(String username,  Integer userId, String role) {
        Map<String, Object> map = new HashMap<>(2);
        map.put(USER_ID_CLAIMS, userId);
        map.put(USER_ROLE_CLAIMS, role);
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .setClaims(map)
                .setIssuer(ISS)
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION * 1000))
                .compact();
    }

    /**
     * 从token中获取用户名
     * @param token token字符串
     * @return 用户名
     */
    public static String getUsername(String token) throws PermissionException {
        return getTokenBody(token).getSubject();
    }


    /**
     * 获取用户Id
     * @param token token字符串
     * @return 用户id
     */
    public static Integer getUserId(String token) throws PermissionException {
        return Integer.parseInt(getTokenBody(token).get(USER_ID_CLAIMS).toString());
    }

    /**
     * 获取用户角色
     * @param token token字符串
     * @return 用户id
     */
    public static String getUserRole(String token) throws PermissionException {
        return getTokenBody(token).get(USER_ROLE_CLAIMS).toString();
    }

    /**
     * 是否已过期
     * @param token token字符串
     * @return 是否过期？
     */
    public static boolean isExpiration(String token){
        try {
            return getTokenBody(token).getExpiration().before(new Date());
        }catch (PermissionException e){
            return false;
        }
    }


    private static Claims getTokenBody(String token) throws PermissionException {
        try {
            return Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token.replace(JwtTokenUtils.TOKEN_PREFIX, ""))
                    .getBody();
        }catch (ExpiredJwtException e){
            e.printStackTrace();
            throw new PermissionException("token已过期");
        }
    }
}
