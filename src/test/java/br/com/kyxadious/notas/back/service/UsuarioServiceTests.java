package br.com.kyxadious.notas.back.service;

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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    private static final String USERNAME = "admin";

    @Before
    public void setUp() {
        Nota nota1 = new Nota();
        nota1.setTexto("Comprar pão");

        Nota nota2 = new Nota();
        nota2.setTexto("Ir ao mercado");

        ArrayList<Nota> notas = new ArrayList<>(Arrays.asList(nota1, nota2));

        Usuario usuario = new Usuario();
        usuario.setUsername(USERNAME);
        usuario.setPassword("123456");
        usuario.setNotas(notas);
        Usuario usuarioSalvo = this.usuarioService.save(usuario);

        assertNotNull(usuarioSalvo);
        assertNotNull(usuarioSalvo.getNotas());
        assertEquals(2, usuarioSalvo.getNotas().size());
    }

    @After
    public void tearDown() {
        this.usuarioService.deleteAll();
    }

    @Test
    @Transactional
    public void testSalvarUsuarioValido() {
        Usuario usuarioEncontrado = this.usuarioService.findByUsername(USERNAME);

        assertNotNull(usuarioEncontrado);
        assertNotNull(usuarioEncontrado.getNotas());
        assertEquals(USERNAME, usuarioEncontrado.getUsername());
        assertEquals(2, usuarioEncontrado.getNotas().size());
    }

    @Test
    @Transactional
    public void testSalvarUsuarioInvalido() {
        Usuario usuarioEncontrado = this.usuarioService.findByUsername("user");

        assertNull(usuarioEncontrado);
    }

    @Test
    @Transactional
    public void testListarUsuarios() {
        List<Usuario> usuarios = this.usuarioService.findAll();

        assertNotNull(usuarios);
        assertTrue(usuarios.size() > 0);
    }
}
