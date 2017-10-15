package br.com.kyxadious.notas.back.common.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created by alessandro on 15/10/17.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class BCryptUtilsTests {

    private final BCryptPasswordEncoder bCryptEncoder = new BCryptPasswordEncoder();

    @Test
    public void testGerarHash() {
        String senha = "123456";
        String hash = BCryptUtils.gerarHashBCrypt(senha);

        assertTrue(bCryptEncoder.matches(senha, hash));
    }

    @Test
    public void testGerarHashComSenhaErrada() {
        String senha = "123456";
        String senhaErrada = "654321";
        String hash = BCryptUtils.gerarHashBCrypt(senha);

        assertFalse(bCryptEncoder.matches(senhaErrada, hash));
    }

    @Test
    public void testGerarHashComSenhaNuka() {
        String hash = BCryptUtils.gerarHashBCrypt(null);

        assertNull(hash);
    }
}
