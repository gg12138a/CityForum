package cn.edu.zjou.utils;

import cn.edu.zjou.mapper.AdminMapper;
import cn.edu.zjou.po.Admin;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TokenUtils {

    private static final Logger logger = LoggerFactory.getLogger(TokenUtils.class);

    /**
     * 过期时间
     */
    private static final long EXPIRE_TIME = 24 * 60 * 60 * 1000;

    /**
     * 盐密码
     */
    private static final String TOKEN_SECRET = "8cRphh7AC83JtYwrBv";


    private final AdminMapper adminMapper;

    public TokenUtils(AdminMapper adminMapper) {
        this.adminMapper = adminMapper;
    }


    public String sign(Admin admin) {
        String token = null;
        try {
            Date expireAt = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            token = JWT.create()
                    .withIssuer("auth0")
                    .withClaim("username", admin.getUsername())
                    .withClaim("password", admin.getPassword())
                    .withClaim("deptId", admin.getDeptId())
                    .withExpiresAt(expireAt)
                    .sign(Algorithm.HMAC256(TOKEN_SECRET));
        } catch (IllegalArgumentException | JWTCreationException e) {
            e.printStackTrace();
        }
        return token;
    }


    public boolean verify(String token) {
        DecodedJWT decodedJWT;

        try {
            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(TOKEN_SECRET)).withIssuer("auth0").build();
            decodedJWT = jwtVerifier.verify(token);
        } catch (IllegalArgumentException | JWTVerificationException e) {
            //抛出错误即为验证不通过
            e.printStackTrace();
            logger.info("认证失败");
            return false;
        }

        String username = decodedJWT.getClaim("username").as(String.class);
        String password = decodedJWT.getClaim("password").as(String.class);
        Admin selectedAdmin = adminMapper.getAdminByUsernamePassword(new Admin(username, password));
        if (selectedAdmin == null) {
            return false;
        }

        ThreadContextUtil.setThreadLocalAdminUser(selectedAdmin);

        logger.info("认证通过：{},过期于：{}", selectedAdmin.getUsername(), decodedJWT.getExpiresAt());
        return true;
    }
}

