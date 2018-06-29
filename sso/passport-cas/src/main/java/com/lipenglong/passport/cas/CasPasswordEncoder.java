package com.lipenglong.passport.cas;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.NumberUtils;
import org.springframework.util.StringUtils;

/**
 * passport-cas password加密类
 * <p/>
 *
 * @author lipenglong
 * @since 1.0-SNAPSHOT
 */
public class CasPasswordEncoder implements PasswordEncoder {
    private String privateKey = PropertiesUtil.getValue("application.properties", "privateKey");
    private final Log logger = LogFactory.getLog(getClass());

    @Override
    public String encode(CharSequence rawPassword) {
        return new BCryptPasswordEncoder().encode(rawPassword);
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        if (StringUtils.isEmpty(encodedPassword)) {
            logger.warn("Empty encoded password");
            return false;
        }
        if (StringUtils.isEmpty(rawPassword)) {
            logger.warn("Empty raw password");
            return false;
        }

        String password = RSAUtil.decrypt(rawPassword.toString(), RSAUtil.getPrivateKey(privateKey));
        String timeStr = password.substring(password.lastIndexOf("-") + 1);
        if (System.currentTimeMillis() - NumberUtils.parseNumber(timeStr, Long.class) > 1000 * 60) {
            return false;
        }
        password = password.substring(0, password.lastIndexOf("-"));

        return new BCryptPasswordEncoder().matches(password, encodedPassword);
    }

    public static void main(String[] args) {
        System.out.println(new BCryptPasswordEncoder().matches("1234567", "$2a$10$EOtwWCwdofk6THDn9AHZrO1qJVnOkqYqTJts39dDQLnv0VMuDo6xi"));
    }
}
