package br.com.kyxadious.notas.back.commons.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Created by alessandro on 14/10/17.
 */

public class BCryptUtils {

    private static final int LOG_ROUNDS = 5;
    private static final Logger LOG = LoggerFactory.getLogger(BCryptUtils.class);

    private BCryptUtils() {
    }

    public static String gerarHashBCrypt(String senha) {
        if (senha == null) {
            return senha;
        }

        BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder(LOG_ROUNDS);
        String hash = bCrypt.encode(senha);
        LOG.debug("Hash da senha gerado com BCrypt: " + hash);
        return hash;
    }
}
