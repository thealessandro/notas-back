package br.com.kyxadious.notas.back.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.StringUtils;

/**
 * Created by alessandro on 14/10/17.
 */

public class BCryptUtils {

    private static final int LOG_ROUNDS = 5;
    private static final Logger LOG = LoggerFactory.getLogger(BCryptUtils.class);

    private BCryptUtils() {
    }

    public static String generateHash(String password) {
        if (StringUtils.isEmpty(password))
            return null;

        BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder(LOG_ROUNDS);
        String hash = bCrypt.encode(password);
        return hash;
    }

    public static Boolean isValid(String password, String hash) {
        if (StringUtils.isEmpty(password) || StringUtils.isEmpty(hash))
            return false;

        BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder(LOG_ROUNDS);
        return bCrypt.matches(password, hash);
    }
}
