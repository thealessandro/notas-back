package br.com.kyxadious.notas.back.common.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created by alessandro on 15/10/17.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class BCryptUtilsTests {

    @Test
    public void testGerarHash() {
        String senha = "123456";
        String hash = BCryptUtils.gerarHashBCrypt(senha);

        assertTrue(BCryptUtils.senhaValida(senha, hash));
    }

    @Test
    public void testGerarHashComSenhaErrada() {
        String senha = "123456";
        String senhaErrada = "654321";
        String hash = BCryptUtils.gerarHashBCrypt(senha);

        assertFalse(BCryptUtils.senhaValida(senhaErrada, hash));
    }

    @Test
    public void testGerarHashComSenhaNuka() {
        String hash = BCryptUtils.gerarHashBCrypt(null);

        assertNull(hash);
    }
}
