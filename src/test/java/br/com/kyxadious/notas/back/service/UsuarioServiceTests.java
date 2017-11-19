package br.com.kyxadious.notas.back.service;

import br.com.kyxadious.notas.back.common.utils.BCryptUtils;
import br.com.kyxadious.notas.back.domain.Nota;
import br.com.kyxadious.notas.back.domain.Usuario;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static org.junit.Assert.*;

/**
 * Created by alessandro on 15/10/17.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class UsuarioServiceTests {

    @Autowired
    private UsuarioService usuarioService;

    @Test
    @Transactional
    public void testSaveUsuario() {
        Usuario usuario = new Usuario();
        usuario.setUsername("admin");
        usuario.setPassword("123456");
        Usuario usuarioSaved = this.usuarioService.save(usuario);

        assertNotNull(usuarioSaved);
        assertEquals("admin", usuarioSaved.getUsername());
        assertTrue(BCryptUtils.isValid("123456", usuarioSaved.getPassword()));
    }

    @Test
    @Transactional
    public void testSaveUsuarioWithUsernameEmpty() {
        Usuario usuario = new Usuario();
        usuario.setUsername("");
        usuario.setPassword("123456");
        Usuario usuarioSaved = this.usuarioService.save(usuario);

        assertNull(usuarioSaved);
    }

    @Test
    @Transactional
    public void testSaveUsuarioWithUsernameIsNull() {
        Usuario usuario = new Usuario();
        usuario.setUsername(null);
        usuario.setPassword("123456");
        Usuario usuarioSaved = this.usuarioService.save(usuario);

        assertNull(usuarioSaved);
    }

    @Test
    @Transactional
    public void testSaveUsuarioWithPasswordEmpty() {
        Usuario usuario = new Usuario();
        usuario.setUsername("admin");
        usuario.setPassword("");
        Usuario usuarioSaved = this.usuarioService.save(usuario);

        assertNull(usuarioSaved);
    }

    @Test
    @Transactional
    public void testSaveUsuarioWithPasswordIsNull() {
        Usuario usuario = new Usuario();
        usuario.setUsername("admin");
        usuario.setPassword(null);
        Usuario usuarioSaved = this.usuarioService.save(usuario);

        assertNull(usuarioSaved);
    }

    @Test
    @Transactional
    public void testSaveUsuarioWithUsernameAndPasswordAreEmpties() {
        Usuario usuario = new Usuario();
        usuario.setUsername("");
        usuario.setPassword("");
        Usuario usuarioSaved = this.usuarioService.save(usuario);

        assertNull(usuarioSaved);
    }

    @Test
    @Transactional
    public void testSaveUsuarioWithUsernameAndPasswordAreNull() {
        Usuario usuario = new Usuario();
        usuario.setUsername(null);
        usuario.setPassword(null);
        Usuario usuarioSaved = this.usuarioService.save(usuario);

        assertNull(usuarioSaved);
    }

    @Test
    @Transactional
    public void testSaveUsuarioWithDuplicateUsername() {
        Usuario usuario1 = new Usuario();
        usuario1.setUsername("admin");
        usuario1.setPassword("123456");
        Usuario usuarioSaved1 = this.usuarioService.save(usuario1);

        Usuario usuario2 = new Usuario();
        usuario2.setUsername("admin");
        usuario2.setPassword("654321");
        Usuario usuarioSaved2 = this.usuarioService.save(usuario2);

        assertNotNull(usuarioSaved1);
        assertNull(usuarioSaved2);
    }
}
