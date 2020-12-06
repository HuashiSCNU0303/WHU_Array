package com.array.arrayserver.constants;

/**
 * @author yee
 */
public class SecurityConstants {
    /**
     * 登录的地址
     */
    public static final String AUTH_LOGIN_URL = "/auth/login";

    /**
     * 角色的key
     **/
    public static final String ROLE_CLAIMS = "roles";
    /**
     * rememberMe 为 false 的时候过期时间是1个小时
     */
    public static final long EXPIRATION = 60 * 60L;
    /**
     * rememberMe 为 true 的时候过期时间是7天
     */
    public static final long EXPIRATION_REMEMBER = 60 * 60 * 24 * 7L;

    /**
     * JWT签名密钥硬编码到应用程序代码中，应该存放在环境变量或.properties文件中。
     */
    public static final String JWT_SECRET_KEY = "sdfniwehjfoiwejfhkdshfehfkuwehfkuhsaekfhakweufhkwuefsdfniwehjfoiwejfhkdshfehfkuwehfkuhsaekfhakweufhkwuef";

    // JWT token defaults

    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String TOKEN_TYPE = "JWT";

    private SecurityConstants() {
    }
}